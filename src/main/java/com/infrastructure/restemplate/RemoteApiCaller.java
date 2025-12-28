package com.infrastructure.restemplate;

import com.interfaces.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class RemoteApiCaller {

    private final RestTemplate restTemplate;

    public <T> ApiResponse<T> post(
            String url,
            Object request,
            ParameterizedTypeReference<ApiResponse<T>> responseType
    ) {
        ResponseEntity<ApiResponse<T>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        new HttpEntity<>(request),
                        responseType
                );

        return response.getBody();
    }

    public <T> ApiResponse<T> get(
            String url,
            ParameterizedTypeReference<ApiResponse<T>> responseType
    ) {
        ResponseEntity<ApiResponse<T>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        responseType
                );

        return response.getBody();
    }
    public void postWithoutResponse(String url, Object request) {
        restTemplate.postForLocation(url, request);
    }
    public <T> ResponseEntity<T> exchange(
            String url,
            HttpMethod method,
            HttpEntity<?> requestEntity,
            Class<T> responseType
    ) {
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }
}
