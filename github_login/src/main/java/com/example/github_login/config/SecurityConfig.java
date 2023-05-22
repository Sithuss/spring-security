package com.example.github_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.oauth2Login();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
    @Bean
    public ClientRegistrationRepository clientRepository(){
        var c = clientRegistration();

        return new InMemoryClientRegistrationRepository(c);
    }

    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider
                .GITHUB
                .getBuilder("github")
                .clientId("7eb82ac429f6b8a1abf7")
                .clientSecret("c1ea093da4f6e03ca6fe834fac6c780bd45cd363")
                .build();
    }
}
