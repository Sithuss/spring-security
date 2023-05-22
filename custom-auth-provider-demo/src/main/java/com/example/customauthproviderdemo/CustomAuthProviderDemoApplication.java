package com.example.customauthproviderdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaml.snakeyaml.DumperOptions;

@SpringBootApplication
public class CustomAuthProviderDemoApplication {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    public static void main(String[] args) {
        SpringApplication.run(CustomAuthProviderDemoApplication.class, args);
    }

}
