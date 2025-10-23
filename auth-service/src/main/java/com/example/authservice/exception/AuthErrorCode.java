package com.example.authservice.exception;

public enum AuthErrorCode {
    // General errors
    INTERNAL_ERROR("AUTH-001", "Internal server error"),
    INVALID_REQUEST("AUTH-002", "Invalid request"),
    
    // Authentication errors
    INVALID_CREDENTIALS("AUTH-101", "Invalid credentials"),
    INVALID_TOKEN("AUTH-102", "Invalid token"),
    INVALID_REFRESH_TOKEN("AUTH-103", "Invalid refresh token"),
    TOKEN_EXPIRED("AUTH-104", "Token expired"),
    TOKEN_GENERATION_FAILED("AUTH-105", "Failed to generate token"),
    
    // OAuth2 specific errors
    INVALID_CLIENT("AUTH-201", "Invalid client"),
    INVALID_GRANT("AUTH-202", "Invalid grant"),
    INVALID_SCOPE("AUTH-203", "Invalid scope"),
    UNAUTHORIZED_CLIENT("AUTH-204", "Unauthorized client"),
    CONSENT_REQUIRED("AUTH-205", "User consent required"),
    CONSENT_DENIED("AUTH-206", "User denied consent"),
    INVALID_REDIRECT_URI("AUTH-207", "Invalid redirect URI"),
    INVALID_STATE("AUTH-208", "Invalid state parameter"),
    INVALID_CODE_VERIFIER("AUTH-209", "Invalid code verifier"),
    TOKEN_EXCHANGE_FAILED("AUTH-210", "Failed to exchange code for token"),
    TOKEN_REFRESH_FAILED("AUTH-211", "Failed to refresh token"),
    
    // Account errors
    ACCOUNT_LOCKED("AUTH-301", "Account is locked"),
    ACCOUNT_DISABLED("AUTH-302", "Account is disabled"),
    ACCOUNT_EXPIRED("AUTH-303", "Account has expired"),
    USER_NOT_FOUND("AUTH-304", "User not found"),
    EMAIL_ALREADY_EXISTS("AUTH-305", "Email already exists"),
    
    // Social auth errors
    SOCIAL_ACCOUNT_NOT_LINKED("AUTH-401", "Social account not linked"),
    SOCIAL_PROVIDER_ERROR("AUTH-402", "Error from social provider"),
    SOCIAL_GOOGLE_FAILED("AUTH-403", "Failed to retrieve user info from Google"),
    SOCIAL_TOKEN_EXCHANGE_FAILED("AUTH-404", "Failed to exchange social token"),
    SOCIAL_EMAIL_NOT_VERIFIED("AUTH-405", "Social email not verified"),
    SOCIAL_ACCOUNT_EXISTS("AUTH-406", "Account already exists with different provider"),
    
    // Rate limiting errors
    TOO_MANY_REQUESTS("AUTH-501", "Too many requests"),
    TOO_MANY_LOGIN_ATTEMPTS("AUTH-502", "Too many login attempts"),
    
    // Session errors
    SESSION_EXPIRED("AUTH-601", "Session has expired"),
    CONCURRENT_SESSION("AUTH-602", "Account logged in from another device"),
    INVALID_SESSION("AUTH-603", "Invalid session"),
    
    // Registration errors
    REGISTRATION_FAILED("AUTH-701", "Registration failed");

    private final String code;
    private final String defaultMessage;

    AuthErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}