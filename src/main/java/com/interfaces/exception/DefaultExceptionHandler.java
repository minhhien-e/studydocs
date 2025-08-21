package com.interfaces.exception;

import com.domain.exception.ValidateMessageError;
import com.interfaces.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    private final MessageSource messageSource;

    public DefaultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handle(MethodArgumentNotValidException exception) {
        Set<ValidateMessageError> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(e -> {
                    String field = e.getField();
                    Object rejectedValue = e.getRejectedValue();
                    String code = e.getCode();
                    Locale locale = LocaleContextHolder.getLocale();

                    ValidateMessageError msg = new ValidateMessageError();
                    msg.setField(field);
                    msg.setRejectedValue(rejectedValue);
                    msg.setMessage(messageSource.getMessage(code, new Object[]{rejectedValue}, locale));

                    return msg;
                })
                .collect(Collectors.toSet());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
