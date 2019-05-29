package com.grsu.game.platform;

import com.grsu.game.platform.ticktacktoe.field.BasicField;

public class ConsoleField extends BasicField {

    public ConsoleField(int fieldSize) {
        super(fieldSize);
    }

    @Override
    public void render() {
        System.out.println("=============");
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(" ");
                System.out.print(field[i][j] != null ? field[i][j].sign() : "_");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }
}
