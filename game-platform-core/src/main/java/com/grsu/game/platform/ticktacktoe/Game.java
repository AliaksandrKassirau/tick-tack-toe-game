package com.grsu.game.platform.ticktacktoe;

import com.grsu.game.platform.ticktacktoe.events.FieldChangedEvent;
import com.grsu.game.platform.ticktacktoe.field.Field;
import com.grsu.game.platform.ticktacktoe.field.Figure;
import com.grsu.game.platform.ticktacktoe.player.GamePlayer;
import com.grsu.game.platform.ticktacktoe.player.NoPlayersMoveException;
import com.grsu.game.platform.ticktacktoe.player.PlayerInfo;

import java.util.*;

public class Game <T extends PlayerInfo & GamePlayer> extends Observable {
    private static final long WAITING_PLAYERS_TIMEOUT = 20000L;
    private Status gameStatus = Status.CREATED;
    private final Field field;
    private final Stack<Figure> figuresStack;
    private final Map<String, T> playersByIds = new HashMap<>();
    private final Map<Figure, T> playersByFigures = new EnumMap<>(Figure.class);

    public Game(Field field) {
        this.field = field;

        this.figuresStack =  new Stack<>();
        this.figuresStack.push(Figure.TIC_TAC);
        this.figuresStack.push(Figure.TOE);
        Collections.shuffle(this.figuresStack);
    }

    public synchronized Game joinGameAsPlayer(T player) {
        if (!playersByIds.containsKey(player.getPlayersId()) && playersByIds.size() == 2) {
            throw new IllegalStateException("Exceeds limit of players in one game");
        }
        if (!playersByIds.containsKey(player.getPlayersId())) {
            playersByFigures.put(figuresStack.pop(), player);
        }
        playersByIds.put(player.getPlayersId(), player);

        if (playersByIds.size() == 2) {
            gameStatus = Status.READY;
            notify();
        }

        return this;
    }

    public Field getField() {
        return field;
    }

    public GameStats start() throws InterruptedException {
        if (gameStatus != Status.READY) {
            gameStatus = Status.WAITING_FOR_PLAYERS;
            synchronized(this) {
                wait(WAITING_PLAYERS_TIMEOUT);
            }
        }
        if (gameStatus != Status.READY) {
            throw new IllegalStateException("Game can't be started because no all players joined");
        }
        gameStatus = Status.IN_PROGRESS;
        initPlayers();

        notifyFieldChanged();

        do {
            for (Map.Entry<Figure, T> entry : playersByFigures.entrySet()) {
                PlayersMove move = makeMove(entry.getValue());
                field.setMove(entry.getKey(), move);
                notifyFieldChanged();

                if (field.isGameFinished())
                    break;
            }
        } while (!field.isGameFinished());

        gameStatus = Status.FINISHED;

        return new GameStats();
    }

    private void initPlayers() {
    }

    private void notifyFieldChanged() {
        setChanged();
        notifyObservers(new FieldChangedEvent(field.getCopyOfTheField()));
    }

    private PlayersMove makeMove(GamePlayer player) {
        PlayersMove move = null;
        try {
            move = player.makeMove();
            if (!field.isMoveValid(move)) {
                move = makeMove(player);
            }
        } catch (NoPlayersMoveException e) {
            throw new IllegalStateException("No player's move", e);
        }

        return move;
    }


    public enum Status {
        CREATED,
        WAITING_FOR_PLAYERS,
        READY,
        IN_PROGRESS,
        FINISHED
    }
}
