package com.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpExeption extends RuntimeException {
    int statusCode;
    Integer erorCode;
}
