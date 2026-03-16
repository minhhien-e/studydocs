package studydocs.notification.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import studydocs.notification.shared.web.ApiResponse;
import studydocs.notification.shared.web.HttpException;


@Component
@RequiredArgsConstructor
public class RemoteApiCaller {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public <T> T getForEntity(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            Object... uriVariables) {

        return exchange(HttpMethod.GET, url, responseType, null, uriVariables).data();
    }

    public <T> T postForEntity(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables) {

        return exchange(HttpMethod.POST, url, responseType, requestEntity, uriVariables).data();
    }


    /**
     * Phương thức tổng quát — có thể dùng cho mọi HTTP method
     */
    private <T> ApiResponse<T> exchange(
            HttpMethod method,
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables) {
        try {
            ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                    url,
                    method,
                    requestEntity,
                    responseType,
                    uriVariables
            );
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            String responseBody = ex.getResponseBodyAsString();
            ApiResponse<?> error = null;
            try {
                error = objectMapper.readValue(responseBody, ApiResponse.class);
            } catch (Exception ignored) {
            }
            assert error != null;
            throw new HttpException("Failed to communicate with remote service", error.statusCode(), error.errorCode());
        }
    }
}

