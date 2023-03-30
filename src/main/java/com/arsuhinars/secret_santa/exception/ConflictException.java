package com.arsuhinars.secret_santa.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends AppException {
    public ConflictException() {
        super(HttpStatus.CONFLICT, "Conflict error");
    }

    public ConflictException(String details) {
        super(HttpStatus.CONFLICT, details);
    }

    public ConflictException(HttpStatus status, String details) {
        super(status, details);
    }
}
