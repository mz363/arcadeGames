package com.mz363.arcadegames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/signup")
    public String signup() {
        return "pages/signup.html";
    }

    @RequestMapping("/userPage")
    public String userPage() {
        return "pages/userPage.html";
    }

    @RequestMapping("/algebraGame")
    public String algebraGame() {
        return "pages/algebraGame.html";
    }
}
