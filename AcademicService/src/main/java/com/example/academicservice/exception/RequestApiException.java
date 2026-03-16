package com.example.academicservice.exception;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RequestApiException extends RuntimeException {

    private final int status;
    private final int errorCode;

}

