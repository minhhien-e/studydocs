package com.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // --- Common ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E000"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E001"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E002"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "E003"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "E004"),

    // --- User ---
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "U002"),
    INVALID_USER_INPUT(HttpStatus.BAD_REQUEST, "U003"),

    // --- Authentication ---
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A001"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "A002"),

    // --- Business logic ---
    OPERATION_NOT_ALLOWED(HttpStatus.FORBIDDEN, "B001"),
    INVALID_RANGE(HttpStatus.BAD_REQUEST, "B002"),
    // --- Repository / DB ---
    SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB001"),
    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB002"),
    DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "DB003"),

    // --- System ---
    NO_HANDLER(HttpStatus.NO_CONTENT, "NO_HANDLER"),

   ;

    private final HttpStatus status;
    private final String code;

    ErrorCode(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }
}
