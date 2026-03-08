package com.v1.SmartCapital.exception;

import org.springframework.http.HttpStatus;

public class FileValidationException extends HttpStatusException {
    public FileValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
