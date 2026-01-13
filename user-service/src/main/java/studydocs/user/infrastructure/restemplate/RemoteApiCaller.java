package studydocs.user.infrastructure.restemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import studydocs.user.interfaces.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class RemoteApiCaller {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public <T> ApiResponse<T> post(
            String url,
            Object request,
            MediaType headerType,
            ParameterizedTypeReference<ApiResponse<T>> responseType
    ) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(headerType);


            ResponseEntity<ApiResponse<T>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            new HttpEntity<>(request, headers),
                            responseType
                    );

            return response.getBody();
        } catch (HttpClientErrorException ex) {
            try {
                String body = ex.getResponseBodyAsString();

                //  convert ParameterizedTypeReference -> JavaType
                JavaType javaType = mapper.getTypeFactory()
                        .constructType(responseType.getType());

                return mapper.readValue(body, javaType);

            } catch (Exception parseEx) {
                // fallback nếu body không parse được
                return ApiResponse.error(
                        ex.getStatusCode().value(),
                        -1,
                        null
                );
            }
        }
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
