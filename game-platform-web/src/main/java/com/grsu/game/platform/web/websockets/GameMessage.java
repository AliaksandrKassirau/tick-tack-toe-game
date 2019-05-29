package com.grsu.game.platform.web.websockets;

import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameMessage {
    private String type;
    private JsonElement payload;
}
