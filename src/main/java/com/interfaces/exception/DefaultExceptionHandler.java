package com.interfaces.exception;

import com.error.exception.ValidateMessageError;
import com.interfaces.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handle(MethodArgumentNotValidException exception,
                                            HttpServletRequest request) {

        String traceId = request.getHeader(TRACE_ID_HEADER);

        LOG.error("[traceId: {}] Validation failed: {}", traceId, exception.getMessage());

        Set<ValidateMessageError> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(e -> {
                    ValidateMessageError msg = new ValidateMessageError();
                    msg.setField(e.getField());
                    msg.setRejectedValue(e.getRejectedValue());
                    msg.setMessage(e.getDefaultMessage()); // lấy message trực tiếp
                    return msg;
                })
                .collect(Collectors.toSet());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}

