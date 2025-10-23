package com.example.authservicev2.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String code;
    private String message;
    private String traceId; // optional, để debug cross-service
    private LocalDateTime timestamp;

    public ErrorResponse(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
        this.timestamp = LocalDateTime.now();
    }

    // getters, setters
}