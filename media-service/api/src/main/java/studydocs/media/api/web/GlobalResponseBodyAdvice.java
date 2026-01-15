package studydocs.media.api.web;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import studydocs.media.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.media.shared.dto.Unit;
import studydocs.media.shared.web.ApiResponse;

@RestControllerAdvice(basePackages = "studydocs.media.api.web")
@RequiredArgsConstructor
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final CurrentTraceIdProvider currentTraceIdProvider;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {

        if (body instanceof ApiResponse) {
            return body;
        }

        if (body instanceof String) {
            return body;
        }

        if (body instanceof Unit) {
            body = null;
        }
        return ApiResponse.success(body, currentTraceIdProvider.getCurrentTraceId());
    }
}
