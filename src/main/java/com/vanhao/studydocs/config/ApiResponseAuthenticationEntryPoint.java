package com.vanhao.studydocs.config;

import com.vanhao.studydocs.exception.AuthErrorCodes;
import com.vanhao.studydocs.web.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Writes 401 errors in ApiResponse format.
 */
public class ApiResponseAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public ApiResponseAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        if (response.isCommitted()) {
            return;
        }

        int status = HttpServletResponse.SC_UNAUTHORIZED;
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(
                response.getOutputStream(),
                ApiResponse.error(status, AuthErrorCodes.ACCESS_TOKEN_INVALID_OR_EXPIRED));
    }
}
