package com.example.authservice.exception;

import org.springframework.http.HttpStatus;

public class OAuth2AuthenticationException extends BaseException {
    
    public OAuth2AuthenticationException(AuthErrorCode errorCode, String message) {
        super(errorCode, message, HttpStatus.UNAUTHORIZED);
    }

    public OAuth2AuthenticationException(AuthErrorCode errorCode) {
        super(errorCode, errorCode.getDefaultMessage(), HttpStatus.UNAUTHORIZED);
    }
}