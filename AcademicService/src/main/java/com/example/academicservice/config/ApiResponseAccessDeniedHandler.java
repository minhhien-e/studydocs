package com.example.academicservice.config;

import com.example.academicservice.exception.AuthErrorCodes;
import com.example.academicservice.web.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Writes 403 errors in ApiResponse format.
 */
public class ApiResponseAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public ApiResponseAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        if (response.isCommitted()) {
            return;
        }

        int status = HttpServletResponse.SC_FORBIDDEN;
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(
                response.getOutputStream(),
                ApiResponse.error(status, AuthErrorCodes.FORBIDDEN)
        );
    }
}

