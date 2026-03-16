package com.studydocs.media.core.exception;

import com.studydocs.media.core.exception.enums.ErrorCode;

import java.util.Map;

public class InvalidSignatureException extends BaseException {
    public InvalidSignatureException(String message) {
        super(ErrorCode.INVALID_SIGNATURE, message, Map.of());
    }

    public InvalidSignatureException(String message, Map<String, Object> attributes) {
        super(ErrorCode.INVALID_SIGNATURE, message, attributes);
    }
}
