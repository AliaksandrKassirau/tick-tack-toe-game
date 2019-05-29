package com.grsu.game.platform;

import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.ticktacktoe.Game;
import com.grsu.game.platform.ticktacktoe.GameStats;
import com.grsu.game.platform.ticktacktoe.player.RandomMovePlayer;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        User bot1 = new User();
        User bot2 = new User();
        do {
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    Game game = new Game(new NoRenderField(3));
                    game.joinGame(new RandomMovePlayer(bot1));
                    game.joinGame(new RandomMovePlayer(bot2));

                    game.addObserver(new ConsoleFieldChangedEventHandler());

                    GameStats gameStats = game.start();
                    break;
                default:
                    break;
            }
        } while (true);
    }
}
