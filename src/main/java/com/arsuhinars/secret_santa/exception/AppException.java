package com.arsuhinars.secret_santa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final HttpStatus status;

    private final String details;

    public AppException(HttpStatus status, String details) {
        super(details);

        this.status = status;
        this.details = details;
    }
}
