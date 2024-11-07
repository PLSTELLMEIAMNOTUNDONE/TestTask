package org.example.controllers;

import org.example.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "org")
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<String> handleException(HttpException e) {
        return new ResponseEntity<>(e.msg, e.HTTP_CODE);
    }
}
