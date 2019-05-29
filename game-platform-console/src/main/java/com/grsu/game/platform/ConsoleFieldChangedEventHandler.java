package com.grsu.game.platform;

import com.grsu.game.platform.ticktacktoe.events.FieldChangedEvent;
import com.grsu.game.platform.ticktacktoe.field.Figure;

import java.util.Observable;
import java.util.Observer;

public class ConsoleFieldChangedEventHandler implements Observer {
    @Override
    public void update(Observable o, Object event) {
        if (event instanceof FieldChangedEvent) {
            FieldChangedEvent fieldChangedEvent = (FieldChangedEvent) event;

            Figure[][] field = fieldChangedEvent.getField();

            for (int i = 0; i < field.length; i++) {
                field[i][0] = Figure.TIC_TAC;
            }

            System.out.println("=============");
            for (int i = 0; i <field.length; i++) {
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
}
