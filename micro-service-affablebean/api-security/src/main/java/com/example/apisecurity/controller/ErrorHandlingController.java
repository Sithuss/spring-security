package com.example.apisecurity.controller;

import com.example.apisecurity.exception.NoBearerError;
import com.example.apisecurity.exception.PasswordNotMatchError;
import com.example.apisecurity.exception.UserNotFound;
import com.example.apisecurity.exception.WrongPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler({PasswordNotMatchError.class,
    UserNotFound.class, WrongPassword.class,
    NoBearerError.class})
    public ResponseEntity handleException(Throwable throwable) throws Exception {
        if (throwable instanceof PasswordNotMatchError error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
        else if (throwable instanceof UserNotFound error) {
            return ResponseEntity.notFound().build();
        }
        else if (throwable instanceof WrongPassword error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
        else if (throwable instanceof NoBearerError error) {
            return ResponseEntity.status(401).body(error.getMessage());
        }
        return null;
    }
}
