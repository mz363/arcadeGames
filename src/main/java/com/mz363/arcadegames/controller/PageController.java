package com.mz363.arcadegames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("register")
    public String getRegister() {
        return "register";
    }

    @GetMapping("userPage")
    public String getUserPage() {
        return "userPage";
    }

    @RequestMapping("algebraGame")
    public String getAlgebraGame() {
        return "algebraGame";
    }
}
