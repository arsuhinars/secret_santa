package com.arsuhinars.secret_santa.exception.handler;

import com.arsuhinars.secret_santa.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<String> handleAppException(HttpServletRequest request, AppException ex) {
        return new ResponseEntity<>(ex.getDetails(), ex.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(HttpServletRequest request, ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
