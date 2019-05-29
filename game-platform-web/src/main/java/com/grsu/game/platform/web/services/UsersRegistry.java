package com.grsu.game.platform.web.services;

import com.grsu.game.platform.shared.User;

public interface UsersRegistry {

    String signUp(User user);

    boolean isSessionValid(String session);

    User getUserBySession(String session);
}
