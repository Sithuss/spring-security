package com.example.methodfilter.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @PreAuthorize("hasAuthority('read')")
    public String greet() {
        return "MAY THE FORCE BE WITH YOU!";
    }
}
