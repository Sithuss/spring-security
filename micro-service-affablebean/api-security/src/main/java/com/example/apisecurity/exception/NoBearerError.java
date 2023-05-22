package com.example.apisecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NoBearerError extends ResponseStatusException {
    public NoBearerError() {
        super(HttpStatus.UNAUTHORIZED, "No Bearer found!");
    }
}
