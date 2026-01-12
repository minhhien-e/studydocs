package studydocs.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import studydocs.dto.response.ApiResponse;
import studydocs.exception.RemoteException;

@Component
@RequiredArgsConstructor
public class RemoteApiCaller {

    private final RestTemplate restTemplate;
    private final jakarta.servlet.http.HttpServletRequest currentRequest;

    public <T> T get(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            Object... uriVariables) {
        return exchange(HttpMethod.GET, url, responseType, null, uriVariables).data();
    }

    public <T> T post(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables) {
        return exchange(HttpMethod.POST, url, responseType, requestEntity, uriVariables).data();
    }

    public <T> ApiResponse<T> exchange(
            HttpMethod method,
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables) {
        // Create headers
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        if (requestEntity != null) {
            headers.addAll(requestEntity.getHeaders());
        }

        // Auto-inject Bearer token
        injectToken(headers);

        HttpEntity<?> newEntity = new HttpEntity<>(requestEntity != null ? requestEntity.getBody() : null, headers);

        ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                url,
                method,
                newEntity,
                responseType,
                uriVariables);

        ApiResponse<T> body = response.getBody();

        if (body == null) {
            throw new RemoteException(response.getStatusCode().value());
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RemoteException(response.getStatusCode().value());
        }

        return body;
    }

    private void injectToken(org.springframework.http.HttpHeaders headers) {
        // 1. Try from Security Context (Authenticated Safe Mode)
        var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null
                && authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            headers.setBearerAuth(jwt.getTokenValue());
            return;
        }

        // 2. Fallback: Try from Raw Request Header (Bypass Mode)
        try {
            String authHeader = currentRequest.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                headers.set("Authorization", authHeader);
            }
        } catch (Exception ignored) {
            // Ignore if request context is not available
        }
    }
}
