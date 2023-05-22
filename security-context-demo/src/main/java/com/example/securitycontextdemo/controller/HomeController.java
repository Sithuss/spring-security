package com.example.securitycontextdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/error1")
    public String error() {
        return "error.html";
    }
}
