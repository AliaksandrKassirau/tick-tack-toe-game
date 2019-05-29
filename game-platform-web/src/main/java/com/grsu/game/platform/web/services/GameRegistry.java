package com.grsu.game.platform.web.services;

import com.grsu.game.platform.ticktacktoe.Game;

public interface GameRegistry {

    String createGameWithBot();

    Game getGameById(String gameId);
}
