package com.grsu.game.platform.web.services;

import com.grsu.game.platform.shared.User;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UsersRegistryImpl implements UsersRegistry, SessionRegistry {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Session> sessions = new HashMap<>();

    public synchronized String signUp(User user) {
        String sessionId = UUID.nameUUIDFromBytes(user.getEmail().getBytes()).toString();
        users.put(sessionId, user);
        return sessionId;
    }

    public boolean isSessionValid(String session) {
        return users.containsKey(session);
    }

    @Override
    public User getUserBySession(String session) {
        return users.get(session);
    }

    @Override
    public void bindPlayerSessionToSocketConnection(String playerSession, Session socketSession) throws IOException {
        Session previousSession = this.sessions.put(playerSession, socketSession);
        if (previousSession != null && previousSession.isOpen()) {
            previousSession.close();
        }
    }
}
