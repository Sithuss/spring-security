package com.example.businessapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class BusinessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessAppApplication.class, args);
    }

}
