package com.grsu.game.platform.web.websockets.encoders;

import com.google.gson.Gson;
import com.grsu.game.platform.ticktacktoe.field.BasicField;
import com.grsu.game.platform.web.websockets.GameMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class BasicFieldEncoder implements Encoder.Text<BasicField> {

    private static Gson gson = new Gson();

    @Override
    public String encode(BasicField message) throws EncodeException {
        return gson.toJson(message);
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