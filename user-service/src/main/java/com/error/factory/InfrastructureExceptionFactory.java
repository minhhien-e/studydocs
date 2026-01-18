package com.error.factory;

import com.error.ErrorCode;
import com.error.exception.InfrastructureException;

public class InfrastructureExceptionFactory {

    public static InfrastructureException custom(ErrorCode code, String method) {
        return new InfrastructureException(code, method);
    }

    public static InfrastructureException custom(ErrorCode code, String method, Throwable cause) {
        return new InfrastructureException(code, method, cause);
    }
}
