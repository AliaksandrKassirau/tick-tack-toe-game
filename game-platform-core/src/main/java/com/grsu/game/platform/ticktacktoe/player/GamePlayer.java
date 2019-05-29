package com.grsu.game.platform.ticktacktoe.player;

import com.grsu.game.platform.ticktacktoe.PlayersMove;

public interface GamePlayer {

    PlayersMove makeMove() throws NoPlayersMoveException;
}
