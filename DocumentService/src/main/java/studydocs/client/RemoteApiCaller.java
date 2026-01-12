package studydocs.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import studydocs.dto.response.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Component
@RequiredArgsConstructor
public class RemoteApiCaller {

        private final RestTemplate restTemplate;
        private final jakarta.servlet.http.HttpServletRequest currentRequest;

        public <T> ApiResponse<T> post(
                        String url,
                        Object request,
                        MediaType headerType,
                        ParameterizedTypeReference<ApiResponse<T>> responseType) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(headerType);

                // Auto-inject Bearer token
                injectToken(headers);

                ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                                url,
                                HttpMethod.POST,
                                new HttpEntity<>(request, headers),
                                responseType);

                return response.getBody();
        }

        public <T> ApiResponse<T> get(
                        String url,
                        ParameterizedTypeReference<ApiResponse<T>> responseType) {
                HttpHeaders headers = new HttpHeaders();
                // Auto-inject Bearer token
                injectToken(headers);

                ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                                url,
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                responseType);

                return response.getBody();
        }

        private void injectToken(HttpHeaders headers) {
                // 1. Try from Security Context (Authenticated Safe Mode)
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
                        headers.setBearerAuth(jwt.getTokenValue());
                        return;
                }

                // 2. Fallback: Try from Raw Request Header (Bypass/Test Mode)
                try {
                        String authHeader = currentRequest.getHeader("Authorization");
                        if (authHeader != null && authHeader.startsWith("Bearer ")) {
                                headers.set("Authorization", authHeader);
                        }
                } catch (Exception ignored) {
                        // Ignore if request context is not available
                }
        }

        public void postWithoutResponse(String url, Object request) {
                restTemplate.postForLocation(url, request);
        }

        public <T> ResponseEntity<T> exchange(
                        String url,
                        HttpMethod method,
                        HttpEntity<?> requestEntity,
                        Class<T> responseType) {
                return restTemplate.exchange(url, method, requestEntity, responseType);
        }
}
