package studydocs.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import studydocs.user.interfaces.security.AuthTokenInterceptor;

import java.util.List;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(AuthTokenInterceptor authTokenInterceptor) {
        var restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(authTokenInterceptor));

        return restTemplate;
    }
}
