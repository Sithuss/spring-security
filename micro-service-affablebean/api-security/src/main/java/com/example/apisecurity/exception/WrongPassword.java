package com.example.apisecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class WrongPassword extends ResponseStatusException {
    public WrongPassword() {
        super(HttpStatus.BAD_REQUEST, "Wrong Password!");
    }
}
