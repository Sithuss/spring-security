package com.example.apisecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound extends ResponseStatusException {
    public UserNotFound() {
        super(HttpStatus.NOT_FOUND, "No email found");
    }
}
