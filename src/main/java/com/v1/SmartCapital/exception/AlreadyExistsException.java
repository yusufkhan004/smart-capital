package com.v1.SmartCapital.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends HttpStatusException {
    public AlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
