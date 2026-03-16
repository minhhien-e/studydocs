package studydocs.notification.infrastructure.adapter.web.intercepter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.provider.CurrentTokenProvider;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenInterceptor implements ClientHttpRequestInterceptor {
    private final CurrentTokenProvider tokenProvider;

    @Override
    public @NonNull ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body, @NonNull ClientHttpRequestExecution execution) throws IOException {
        String token = tokenProvider.getCurrentToken();
        if (token != null) {
            request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
        return execution.execute(request, body);
    }
}
