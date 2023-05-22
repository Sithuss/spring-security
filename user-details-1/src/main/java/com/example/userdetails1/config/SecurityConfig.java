package com.example.userdetails1.config;

import com.example.userdetails1.ds.DummyUser;
import com.example.userdetails1.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        DummyUser user1 = new DummyUser("john", "12345", "read");

        DummyUser user2 = new DummyUser("mary", "12345", "write");

        DummyUser user3 = new DummyUser("thomas", "12345", "read");

        List<DummyUser> users =  List.of(user1,user2,user3);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
