package com.example.followerservice.exception;

/**
 * Common error codes shared across services.
 */
public final class CommonErrorCodes {
    private CommonErrorCodes() {}

    /** Request validation failed (e.g. @Valid). */
    public static final int VALIDATION_FAILED = 100;

    /** Generic bad request. */
    public static final int BAD_REQUEST = 101;

    /** Unhandled server error. */
    public static final int INTERNAL_ERROR = 500;
}
