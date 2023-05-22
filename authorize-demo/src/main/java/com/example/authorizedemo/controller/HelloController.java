package com.example.authorizedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/products/product/{code}")
    public String productCode(@PathVariable String code) {
        return code;
    }

    @PostMapping("/sayHello")
    public String sayHello() {
        return "Say Hello";
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hi! How are you doing?";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }
}
