package com.grsu.game.platform.web.websockets;

import com.google.gson.Gson;
import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.ticktacktoe.Game;
import com.grsu.game.platform.ticktacktoe.PlayersMove;
import com.grsu.game.platform.web.services.GameRegistry;
import com.grsu.game.platform.web.services.SessionRegistry;
import com.grsu.game.platform.web.services.UsersRegistry;
import com.grsu.game.platform.web.websockets.decoders.GameMessageDecoder;
import com.grsu.game.platform.web.websockets.encoders.ObjectEncoder;
import com.grsu.game.platform.web.websockets.players.WebSocketPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(
        value = "/game/{gameId}/player/{playerId}",
        decoders = GameMessageDecoder.class,
        encoders = {ObjectEncoder.class},
        configurator = GamePlatformSpringConfigurator.class
)
@Slf4j
public class GameEndpoint {

    private static Gson gson = new Gson();

    @Autowired
    private GameRegistry gameRegistry;

    @Autowired
    private UsersRegistry usersRegistry;

    @Autowired
    private SessionRegistry sessionRegistry;

    private WebSocketPlayer player;

    @OnOpen
    public void onOpen(Session session,
                       @PathParam("gameId") String gameId,
                       @PathParam("playerId") String playerId) throws IOException, EncodeException {

        Game game = gameRegistry.getGameById(gameId);
        User user = usersRegistry.getUserBySession(playerId);
        this.player = new WebSocketPlayer(playerId, user);

        game.joinGameAsPlayer(this.player);

        game.addObserver((o, event) -> {
            try {
                session.getBasicRemote().sendText(gson.toJson(event));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sessionRegistry.bindPlayerSessionToSocketConnection(playerId, session);
        log.info("Connection opened for game: " + gameId + " and player: " + playerId);
    }

    @OnMessage
    public void onMessage(Session session,
                          GameMessage message,
                          @PathParam("gameId") String gameId,
                          @PathParam("playerId") String playerId) throws IOException {
        // Handle new messages
        String type = message.getType();
        switch(type) {
            case "PLAYERS_MOVE":
                PlayersMove playersMove = gson.fromJson(message.getPayload(), PlayersMove.class);
                this.player.moveReceived(playersMove);
                log.info(message.toString());
                break;
            case "REQUEST_FIELD_STATE":
                try {
                    Game game = gameRegistry.getGameById(gameId);
                    session.getBasicRemote().sendObject(game.getField());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                break;
        }

        log.info("Message received: " + type);
    }

    @OnClose
    public void onClose(Session session,
                        @PathParam("gameId") String gameId,
                        @PathParam("playerId") String playerId) throws IOException {
        // WebSocket connection closes
        log.info("Connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        log.info("error occured");
        throwable.printStackTrace();
    }
}
