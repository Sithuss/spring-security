package com.example.securitydemo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/greet")
    public String greeting() {
        return "Greeting";
    }
}
