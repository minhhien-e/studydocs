package com.example.demoauth.exception;

/**
 * Common (non-auth-reserved) error codes.
 *
 * <p>Auth reserves 0..99. Everything here should stay outside that range.
 */
public final class CommonErrorCodes {
    private CommonErrorCodes() {}

    /** Request validation failed (e.g. @Valid). */
    public static final int VALIDATION_FAILED = 100;

    /** Generic bad request. */
    public static final int BAD_REQUEST = 101;

    /** Role not found. */
    public static final int ROLE_NOT_FOUND = 102;

    /** Permission not found. */
    public static final int PERMISSION_NOT_FOUND = 103;

    /** Permission already exists. */
    public static final int PERMISSION_ALREADY_EXISTS = 104;

    /** Unhandled server error. */
    public static final int INTERNAL_ERROR = 500;
}


