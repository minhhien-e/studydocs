package com.example.authservice.exception;

public class ValidationException extends RuntimeException {
    private final AuthErrorCode errorCode;

    public ValidationException(AuthErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AuthErrorCode getErrorCode() {
        return errorCode;
    }
}