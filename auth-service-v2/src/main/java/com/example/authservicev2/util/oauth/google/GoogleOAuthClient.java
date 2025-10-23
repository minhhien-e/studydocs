package com.example.authservicev2.util.oauth.google;

import com.example.authservicev2.domain.enums.Provider;
import com.example.authservicev2.domain.model.response.TokenResponse;
import com.example.authservicev2.exception.CustomExceptions;
import com.example.authservicev2.util.oauth.OAuthProviderClient;
import com.example.authservicev2.util.oauth.SocialUserInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleOAuthClient implements OAuthProviderClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private static final String TOKEN_ENDPOINT = "https://oauth2.googleapis.com/token";
    private static final String USERINFO_ENDPOINT = "https://openidconnect.googleapis.com/v1/userinfo";

    @Override
    public Provider supports() {
        return Provider.GOOGLE;
    }

    @Override
    public String exchangeCodeForAccessToken(String code, String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("code", code);
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("redirect_uri", redirectUri);
        form.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(form, headers);
        ResponseEntity<TokenResponse> res = restTemplate.postForEntity(TOKEN_ENDPOINT, req, TokenResponse.class);
        if (!res.getStatusCode().is2xxSuccessful() || res.getBody() == null || res.getBody().getAccess_token() == null) {
            throw new CustomExceptions.ValidationException("Không lấy được access token từ Google");
        }
        return res.getBody().getAccess_token();
    }

    @Override
    public SocialUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> req = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserInfo> res =
                restTemplate.exchange(USERINFO_ENDPOINT, HttpMethod.GET, req, GoogleUserInfo.class);

        if (!res.getStatusCode().is2xxSuccessful() || res.getBody() == null) {
            throw new CustomExceptions.ValidationException("Không lấy được user info từ Google");
        }
        GoogleUserInfo g = res.getBody();
        return new SocialUserInfo(g.getSub(), g.getEmail(), g.getName(), g.getPicture());
    }

}