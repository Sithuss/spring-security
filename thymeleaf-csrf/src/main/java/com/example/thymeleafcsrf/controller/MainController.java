package com.example.thymeleafcsrf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class.getName());

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name){
        logger.info("Adding Product:: " + name);
        return "main";
    }
}
