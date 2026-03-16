package com.infrastructure.restemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APIconfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
