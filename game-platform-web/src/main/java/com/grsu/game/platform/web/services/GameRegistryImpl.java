package com.grsu.game.platform.web.services;

import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.ticktacktoe.Game;
import com.grsu.game.platform.ticktacktoe.field.BasicField;
import com.grsu.game.platform.ticktacktoe.player.RandomMovePlayer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameRegistryImpl implements GameRegistry {
    private final Map<String, Game> games = new HashMap<>();

    @Override
    public String createGameWithBot() {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game(new BasicField(3))
                .joinGameAsPlayer(new RandomMovePlayer(UUID.randomUUID().toString(), new User()));
        games.put(gameId, game);
        new Thread(() -> {
            try {
              game.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}).start();
        return gameId;
    }

    @Override
    public Game getGameById(String gameId) {
        return games.get(gameId);
    }
}
