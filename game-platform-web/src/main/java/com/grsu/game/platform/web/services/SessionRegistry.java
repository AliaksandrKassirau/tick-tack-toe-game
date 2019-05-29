package com.grsu.game.platform.web.services;

import javax.websocket.Session;
import java.io.IOException;

public interface SessionRegistry {

    void bindPlayerSessionToSocketConnection(String playerSession, Session socketSession) throws IOException;
}
