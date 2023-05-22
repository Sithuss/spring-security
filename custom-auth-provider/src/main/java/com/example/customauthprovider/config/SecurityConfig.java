package com.example.customauthprovider.config;

import com.example.customauthprovider.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.httpBasic();
        http.authenticationProvider(provider);
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }
}
