package com.error.exception;

import com.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private int statusCode;
    private String code;
    private String message;
    private String description;

    public ExceptionMessage(ErrorCode errorCode, String description) {
        this.statusCode = errorCode.getStatus().value();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.description = description;
    }

}
