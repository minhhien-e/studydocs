package com.example.authservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "oauth2")
@Data
public class Oauth2Config {

    private Map<String, Provider> providers;
    private Token token;
    private Map<String, Client> clients;

    @Data
    public static class Provider {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private List<String> scope;
    }

    @Data
    public static class Token {
        private long accessTokenTimeToLive;
        private long refreshTokenTimeToLive;
    }

    @Data
    public static class Client {
        private String id;
        private String secret;
    }
}
