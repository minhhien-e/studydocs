package com.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // --- Common ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 100),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 101),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 102),
    FORBIDDEN(HttpStatus.FORBIDDEN, 103),
    NOT_FOUND(HttpStatus.NOT_FOUND, 104),

    // --- User ---
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 110),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, 111),
    INVALID_USER_INPUT(HttpStatus.BAD_REQUEST, 112),

    // --- Authentication ---
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 120),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, 121),

    // --- Business logic ---
    OPERATION_NOT_ALLOWED(HttpStatus.FORBIDDEN, 130),
    INVALID_RANGE(HttpStatus.BAD_REQUEST, 131),

    // --- Repository / DB ---
    SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 140),
    UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 141),
    DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 142),

    // --- System ---
    NO_HANDLER(HttpStatus.NO_CONTENT, 150),
//    Http
    HTTPFAIL(HttpStatus.EXPECTATION_FAILED, 160);
    private final HttpStatus status;
    private final Integer code;

    ErrorCode(HttpStatus status, Integer code) {
        this.status = status;
        this.code = code;
    }
}
