package com.grsu.game.platform.ticktacktoe.player;

import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.ticktacktoe.PlayersMove;

import java.util.Random;

public class RandomMovePlayer extends AbstractPlayer {
    private Random random = new Random();
    private String session;
    private User user;

    public RandomMovePlayer(String session, User user) {
        this.session = session;
        this.user = user;
    }

    @Override
    public String getPlayersId() {
        return session;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public PlayersMove makeMove() {
        return new PlayersMove(random.nextInt(3), random.nextInt(3));
    }
}
