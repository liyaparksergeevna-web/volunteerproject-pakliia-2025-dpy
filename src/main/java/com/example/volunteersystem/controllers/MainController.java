package com.example.volunteersystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Главная страница
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Публичная информация
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
