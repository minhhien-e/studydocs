package com.example.authservice.service.social;

import com.example.authservice.exception.AuthenticationException;
import com.example.authservice.exception.AuthErrorCode;
import com.example.authservice.model.dto.social.SocialUserInfo;
import com.example.authservice.model.enums.AuthProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleProviderService implements SocialProviderService {

    private static final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    @Override
    public SocialUserInfo getUserInfoFromCode(String authorizationCode, String redirectUri) {
        try {
            // 1. Exchange authorization code for access token
            String accessToken = exchangeCodeForToken(authorizationCode, redirectUri);

            // 2. Use access token to get user info
            return getUserInfo(accessToken);

        } catch (Exception e) {
            log.error("Error getting user info from Google", e);
            throw new AuthenticationException(AuthErrorCode.SOCIAL_GOOGLE_FAILED);
        }
    }

    private String exchangeCodeForToken(String authorizationCode, String redirectUri) {
        // Chuẩn bị request body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        // Chuẩn bị headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Gọi Google Token Endpoint
        ResponseEntity<Map> response = restTemplate.postForEntity(
            GOOGLE_TOKEN_URL,
            new HttpEntity<>(params, headers),
            Map.class
        );

        if (response.getBody() == null || !response.getBody().containsKey("access_token")) {
            throw new AuthenticationException(AuthErrorCode.SOCIAL_TOKEN_EXCHANGE_FAILED);
        }

        return (String) response.getBody().get("access_token");
    }

    private SocialUserInfo getUserInfo(String accessToken) {
        // Chuẩn bị headers với access token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        // Gọi Google UserInfo Endpoint
        ResponseEntity<Map> response = restTemplate.exchange(
            GOOGLE_USER_INFO_URL,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            Map.class
        );

        if (response.getBody() == null) {
            throw new AuthenticationException(AuthErrorCode.SOCIAL_PROVIDER_ERROR);
        }

        Map<String, Object> userInfo = response.getBody();

        // Kiểm tra email verified
        Boolean emailVerified = (Boolean) userInfo.get("email_verified");
        if (emailVerified == null || !emailVerified) {
            throw new AuthenticationException(AuthErrorCode.SOCIAL_EMAIL_NOT_VERIFIED);
        }

        return SocialUserInfo.builder()
            .id((String) userInfo.get("sub"))
            .email((String) userInfo.get("email"))
            .name((String) userInfo.get("name"))
            .imageUrl((String) userInfo.get("picture"))
            .provider(AuthProvider.GOOGLE)
            .emailVerified(true)
            .build();
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }
}