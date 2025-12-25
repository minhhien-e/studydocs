package studydocs.notification.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import studydocs.notification.application.port.in.provider.CurrentTokenProvider;
import studydocs.notification.infrastructure.adapter.web.intercepter.AuthTokenInterceptor;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final CurrentTokenProvider tokenProvider;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
                .add(new AuthTokenInterceptor(tokenProvider));
        return restTemplate;
    }
}
