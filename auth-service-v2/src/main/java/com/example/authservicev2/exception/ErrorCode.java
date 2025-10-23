package com.example.authservicev2.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    VALIDATION_ERROR("ERR-001", "Validation failed"),
    AUTHENTICATION_ERROR("ERR-002", "Authentication failed"),
    AUTHORIZATION_ERROR("ERR-003", "Not authorized"),
    RESOURCE_NOT_FOUND("ERR-004", "Resource not found"),
    INTERNAL_SERVER_ERROR("ERR-500", "Internal server error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}
