package com.grsu.game.platform.web.websockets.players;

import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.ticktacktoe.PlayersMove;
import com.grsu.game.platform.ticktacktoe.player.AbstractPlayer;
import com.grsu.game.platform.ticktacktoe.player.NoPlayersMoveException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebSocketPlayer extends AbstractPlayer {

    private final String session;
    private final User user;
    private PlayersMove latestPlayersMove;

    @Override
    public String getPlayersId() {
        return session;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public synchronized PlayersMove makeMove() throws NoPlayersMoveException {
        try {
            latestPlayersMove = null;
            wait();
            return latestPlayersMove;
        } catch (InterruptedException e) {
            throw new NoPlayersMoveException();
        }
    }


    public synchronized void moveReceived(PlayersMove playersMove) {
        this.latestPlayersMove = playersMove;
        notify();
    }
}
