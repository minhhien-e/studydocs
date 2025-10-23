package com.example.authservicev2.exception;

public class CustomExceptions {
  public  static class ValidationException extends ApiException {
        public ValidationException(String message) {
            super(ErrorCode.VALIDATION_ERROR, message);
        }
    }

    public  static class AuthenticationException extends ApiException {
        public AuthenticationException() {
            super(ErrorCode.AUTHENTICATION_ERROR);
        }
    }

    public  static class AuthorizationException extends ApiException {
        public AuthorizationException() {
            super(ErrorCode.AUTHORIZATION_ERROR);
        }
    }
}

