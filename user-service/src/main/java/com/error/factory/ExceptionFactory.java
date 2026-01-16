package com.error.factory;

import com.error.ErrorCode;
import com.error.exception.DomainException;

public class ExceptionFactory {

    /**
     * Sinh lỗi UserNotFound
     * @param methodName tên phương thức gây lỗi
     */
    public static DomainException userNotFound(String methodName) {
        return new DomainException(ErrorCode.USER_NOT_FOUND, methodName);
    }

    /**
     * Sinh lỗi UserAlreadyExists
     * @param methodName tên phương thức gây lỗi
     */
    public static DomainException userAlreadyExists(String methodName) {
        return new DomainException(ErrorCode.USER_ALREADY_EXISTS, methodName);
    }

    /**
     * Sinh lỗi InvalidImage
     * @param methodName tên phương thức gây lỗi
     */
    public static DomainException invalidImage(String methodName) {
        return new DomainException(ErrorCode.INVALID_USER_INPUT, methodName);
    }

    /**
     * Sinh lỗi chung InternalServerError
     */
    public static DomainException internalError(String methodName) {
        return new DomainException(ErrorCode.INTERNAL_SERVER_ERROR, methodName);
    }
    public static DomainException invalidRange(String methodName) {
        return new DomainException(ErrorCode.INVALID_RANGE, methodName);
    }
    /**
     * Sinh lỗi tùy biến theo ErrorCode
     */
    public static DomainException custom(ErrorCode errorCode, String methodName) {
        return new DomainException(errorCode, methodName);
    }
}

