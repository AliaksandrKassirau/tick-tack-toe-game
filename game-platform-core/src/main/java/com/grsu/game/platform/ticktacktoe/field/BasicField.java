package com.grsu.game.platform.ticktacktoe.field;

import com.grsu.game.platform.ticktacktoe.PlayersMove;

import java.util.Objects;

public class BasicField implements Field {
    protected final Figure[][] field;

    public BasicField(int fieldSize) {
        this.field = new Figure[fieldSize][fieldSize];
    }

    @Override
    public void setMove(Figure figure, PlayersMove move) {
        if (isMoveValid(move)) {
            field[move.i()][move.j()] = figure;
        }
    }

    @Override
    public boolean isMoveValid(PlayersMove move) {
        return field[move.i()][move.j()] == null;
    }

    @Override
    public boolean isGameFinished() {
        return isThereWinner() || isThereRoomForMove();
    }

    @Override
    public boolean isThereWinner() {
        boolean winnerInRows = checkRowsForWinner();
        boolean winnerInColumns = checkColumnsForWinner();
        boolean winnerInDiagonals = checkDiagonalsForWinner();
        return winnerInRows || winnerInColumns || winnerInDiagonals;
    }

    @Override
    public boolean isThereRoomForMove() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Figure[][] getCopyOfTheField() {
        return field;
    }

    private boolean checkColumnsForWinner() {
        for (int columnIndex = 0; columnIndex < field.length; columnIndex++) {
            Figure firstFigureOfTheColumn = field[0][columnIndex];
            boolean isColumnTheSame = true;
            for (int rowIndex = 1; rowIndex < field.length; rowIndex++) {
                if (Objects.isNull(firstFigureOfTheColumn) || Objects.isNull(field[rowIndex][columnIndex])
                        || !Objects.equals(firstFigureOfTheColumn, field[rowIndex][columnIndex])) {
                    isColumnTheSame = false;
                    break;
                }
            }

            if (isColumnTheSame) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRowsForWinner() {
        for (int rowIndex = 0; rowIndex < field.length; rowIndex++) {
            Figure firstFigureOfTheRow = field[rowIndex][0];
            boolean isRowTheSame = true;
            for (int columnIndex = 1; columnIndex < field[rowIndex].length; columnIndex++) {
                if (Objects.isNull(firstFigureOfTheRow) || Objects.isNull(field[rowIndex][columnIndex])
                        || !Objects.equals(firstFigureOfTheRow, field[rowIndex][columnIndex])) {
                    isRowTheSame = false;
                    break;
                }
            }
            if (isRowTheSame) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWinner() {
        boolean isUpperDiagonalTheSame = true;
        boolean isLowerDiagonalTheSame = true;

        int rowsIndex = field.length - 1;

        Figure upperDiagonalFirstFigure = field[0][0];
        Figure lowerDiagonalFirstFigure = field[rowsIndex][0];

        for (int i = 1; i < field.length; i++) {
            if (isUpperDiagonalTheSame
                    && (Objects.isNull(upperDiagonalFirstFigure)
                    || Objects.isNull(field[i][i])
                    || !Objects.equals(upperDiagonalFirstFigure, field[i][i]))) {
                isUpperDiagonalTheSame = false;
            }
            if (isLowerDiagonalTheSame
                    && (Objects.isNull(lowerDiagonalFirstFigure)
                    || Objects.isNull(field[--rowsIndex][i])
                    || !Objects.equals(lowerDiagonalFirstFigure, field[rowsIndex][i]))) {
                isLowerDiagonalTheSame = false;
            }
        }

        return isLowerDiagonalTheSame || isUpperDiagonalTheSame;
    }
}
