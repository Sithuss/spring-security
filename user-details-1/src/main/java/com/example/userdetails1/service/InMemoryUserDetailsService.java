package com.example.userdetails1.service;

import com.example.userdetails1.ds.DummyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<DummyUser> users;

    public InMemoryUserDetailsService(List<DummyUser> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users
                .stream().filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }
}
