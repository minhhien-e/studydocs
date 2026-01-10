package com.error.exception;

import com.error.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String methodName;

    public DomainException(ErrorCode errorCode, String methodName) {
        super();
        this.errorCode = errorCode;
        this.methodName = methodName;
    }
}
