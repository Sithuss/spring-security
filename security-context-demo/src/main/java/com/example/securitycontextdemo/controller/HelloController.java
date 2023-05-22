package com.example.securitycontextdemo.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hanni")
    public String hanni() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service = new DelegatingSecurityContextExecutorService(service);
        try {
            return "Hello " + service.submit(task).get() + "!";
        }finally {
            service.shutdown();
        }
    }

    @GetMapping("/mary")
    public String mary() throws Exception {
        Callable<String> task = () -> {
          SecurityContext context = SecurityContextHolder.getContext();
          return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            return "Hello, " + service.submit(task).get() + "!";
        } finally {
            service.shutdown();
        }
    }

    @GetMapping("/bye")
//    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println("Username::" + username);
    }

    @GetMapping("/greeting")
    public String sayGreeting(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }

    @GetMapping("/hello")
    public String hello (){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Hello, " + authentication.getName() + "!";
    }
}
