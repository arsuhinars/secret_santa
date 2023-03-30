package com.arsuhinars.secret_santa.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Entity was not found");
    }

    public NotFoundException(String details) {
        super(HttpStatus.NOT_FOUND, details);
    }

    public NotFoundException(HttpStatus status, String details) {
        super(status, details);
    }
}
