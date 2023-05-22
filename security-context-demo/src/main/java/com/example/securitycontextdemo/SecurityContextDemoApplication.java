package com.example.securitycontextdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
public class SecurityContextDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityContextDemoApplication.class, args);
    }

}
