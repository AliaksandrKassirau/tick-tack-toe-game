package com.grsu.game.platform.web.controllers;

import com.grsu.game.platform.web.services.GameRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameRegistry gameRegistry;

    @GetMapping("/game-platform/game-with-bot")
    public String gameWithBot(Model model) {
        String gameId = gameRegistry.createGameWithBot();
        return "redirect:/game-platform/game/" + gameId;
    }

    @GetMapping("/game-platform/game/{gameId}")
    public String game(@PathVariable String gameId) {
        return "game";
    }
}
