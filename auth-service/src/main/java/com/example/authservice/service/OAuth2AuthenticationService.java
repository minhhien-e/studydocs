package com.example.authservice.service;

import com.example.authservice.model.dto.response.TokenResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface OAuth2AuthenticationService {
    /**
     * Xử lý authentication sau khi OAuth2 login thành công
     * @param authentication Token chứa thông tin authentication từ OAuth2 provider
     * @return TokenResponse chứa access token và refresh token
     */
    TokenResponse handleOAuth2Authentication(OAuth2AuthenticationToken authentication);
}