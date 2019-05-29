package com.grsu.game.platform.ticktacktoe.field;

import com.grsu.game.platform.ticktacktoe.PlayersMove;

public interface Field {

    void setMove(Figure figure, PlayersMove move);

    boolean isMoveValid(PlayersMove move);

    boolean isGameFinished();

    boolean isThereWinner();

    boolean isThereRoomForMove();

    Figure[][] getCopyOfTheField();
}
