package com.grsu.game.platform.web.controllers;

import com.grsu.game.platform.shared.User;
import com.grsu.game.platform.web.services.UsersRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final UsersRegistry usersRegistry;

    @GetMapping("/sign-up")
    public String signInPage(Model model,
                             HttpServletRequest request) {
        Cookie sessionCookie = WebUtils.getCookie(request, "session");
        if (sessionCookie != null && sessionCookie.getValue() != null && usersRegistry.isSessionValid(sessionCookie.getValue())) {
            return "redirect:/game-platform";
        } else {
            model.addAttribute("signUpModel", new User());
            return "signUp";
        }
    }

    @PostMapping("/sign-up")
    public String signIn(@ModelAttribute("signUpModel") User user,
                         HttpServletResponse response) {
        response.addCookie(new Cookie("session", usersRegistry.signUp(user)));
        return "redirect:/game-platform";
    }
}
