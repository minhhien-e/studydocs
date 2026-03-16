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

    public <T> T get(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            Object... uriVariables
    ) {
        return exchange(HttpMethod.GET, url, responseType, null, uriVariables).data();
    }

    public <T> T post(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables
    ) {
        return exchange(HttpMethod.POST, url, responseType, requestEntity, uriVariables).data();
    }

    public <T> ApiResponse<T> exchange(
            HttpMethod method,
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType,
            HttpEntity<?> requestEntity,
            Object... uriVariables
    ) {
        ResponseEntity<ApiResponse<T>> response = restTemplate.exchange(
                url,
                method,
                requestEntity,
                responseType,
                uriVariables
        );

        ApiResponse<T> body = response.getBody();

        if (body == null) {
            throw new RemoteException(response.getStatusCode().value());
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RemoteException(response.getStatusCode().value());
        }

        return body;
    }
}
