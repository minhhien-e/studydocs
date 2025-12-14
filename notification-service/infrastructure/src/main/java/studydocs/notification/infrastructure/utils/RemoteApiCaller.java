package studydocs.notification.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import studydocs.notification.infrastructure.dto.ApiResponse;
import studydocs.notification.infrastructure.exception.RemoteException;


@Component
@RequiredArgsConstructor
public class RemoteApiCaller {
    private final RestTemplate restTemplate;


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

        ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                url,
                method,
                requestEntity,
                responseType,
                uriVariables
        );
        var body = response.getBody();
        if (body == null)
            throw new RuntimeException();
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RemoteException(body.statusCode(), body.errorCode());
        }

        return body;
    }
}

