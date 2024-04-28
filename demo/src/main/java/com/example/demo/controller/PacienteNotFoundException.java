package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String message) {
        super(message);
    }
    
}
