package com.example.followerservice.config;

import com.example.followerservice.web.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Automatically wraps all controller responses in ApiResponse format.
 * 
 * <p>Success responses will be wrapped with:
 * <ul>
 *   <li>statusCode = 200</li>
 *   <li>errorCode = null</li>
 *   <li>data = original response</li>
 *   <li>traceId = null</li>
 * </ul>
 */
@RestControllerAdvice(basePackages = "com.example.followerservice.controller")
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                   Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                   ServerHttpRequest request, ServerHttpResponse response) {
        // Skip wrapping if already wrapped in ApiResponse
        if (body instanceof ApiResponse) {
            return body;
        }

        // Wrap success response
        return ApiResponse.success(body);
    }
}
