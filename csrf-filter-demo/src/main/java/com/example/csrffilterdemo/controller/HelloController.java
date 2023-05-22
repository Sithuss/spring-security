package com.example.csrffilterdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "getHello!";
    }
    /*

    curl -X POST -H 'Cookie: JSESSIONID=C851AFA293330262CD1CB536EE11E9C2' -H 'X-CSRF-TOKEN: HS6Ky3C9g4EVgIqs19QWGutKZ8P-sK3yoymBS4u-oyM03QAYJEjvqBXY57U4sbOb5vkiI41ySqLOh8zfxRrgKL-Ox0UE6mIr' http://localhost:8080/hello

     */

    @PostMapping("/hello")
    public String postHello() {
        return "postHello";
    }
}
