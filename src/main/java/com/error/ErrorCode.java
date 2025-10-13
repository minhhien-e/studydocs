package com.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // --- Common ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E000", "Internal server error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E001", "Invalid request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E002", "Unauthorized access"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "E003", "Forbidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "E004", "Resource not found"),

    // --- User ---
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "User not found"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "U002", "User already exists"),
    INVALID_USER_INPUT(HttpStatus.BAD_REQUEST, "U003", "Invalid user input"),

    // --- Authentication ---
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A001", "Token expired"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "A002", "Invalid token"),

    // --- Business logic ---
    OPERATION_NOT_ALLOWED(HttpStatus.FORBIDDEN, "B001", "Operation not allowed"),
    // --- Repository / DB ---
    SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB001", "Failed to save entity"),
    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB002", "Failed to update entity"),
    DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB003", "Failed to delete entity"),
//    SYSTEM
    NO_HANDLER(HttpStatus.NO_CONTENT, "NoHandler", "No handler available");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
