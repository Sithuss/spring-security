package com.example.methodsecurity.controller;

import com.example.methodsecurity.ds.Employee;
import com.example.methodsecurity.service.BookService;
import com.example.methodsecurity.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private NameService nameService;
    @Autowired
    private BookService bookService;

    @GetMapping("/hello")
    public String hello() {
        return nameService.getName();
    }

    @GetMapping("/secret/name/{name}")
    public List<String> names(@PathVariable String name) {
        return nameService.getSecretName(name);
    }

    @GetMapping("/book/details/{name}")
    public Employee getDetails(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }
}
