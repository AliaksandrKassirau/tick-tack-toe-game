package com.grsu.game.platform.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamePlatformController {

    @GetMapping("/game-platform")
    public String gamePlatformPage(Model model) {
        model.addAttribute("message", "Hello");
        return "gamePlatform";
    }
}

