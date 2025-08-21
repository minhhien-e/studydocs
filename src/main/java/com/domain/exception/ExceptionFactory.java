package com.domain.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionFactory {

    public ExceptionMessage badRequest(String message, String detail) {
        return new ExceptionMessage(400, message, detail);
    }

    public ExceptionMessage notFound(String message, String detail) {
        return new ExceptionMessage(404, message, detail);
    }

    public ExceptionMessage conflict(String message, String detail) {
        return new ExceptionMessage(409, message, detail);
    }

    public ExceptionMessage systemError(String detail) {
        return new ExceptionMessage(500, "System error", detail);
    }

    public ExceptionMessage unauthorized(String message, String detail) {
        return new ExceptionMessage(401, message, detail);
    }

    public ExceptionMessage forbidden(String message, String detail) {
        return new ExceptionMessage(403, message, detail);
    }
}
