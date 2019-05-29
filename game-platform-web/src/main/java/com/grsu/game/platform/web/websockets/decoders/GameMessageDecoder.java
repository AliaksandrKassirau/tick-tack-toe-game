package com.grsu.game.platform.web.websockets.decoders;

import com.google.gson.Gson;
import com.grsu.game.platform.web.websockets.GameMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class GameMessageDecoder implements Decoder.Text<GameMessage> {

    private static Gson gson = new Gson();

    @Override
    public GameMessage decode(String s) throws DecodeException {
        return gson.fromJson(s, GameMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
