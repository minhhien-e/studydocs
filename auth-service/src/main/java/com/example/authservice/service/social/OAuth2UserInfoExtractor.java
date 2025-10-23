package com.example.authservice.service.social;

import com.example.authservice.model.enums.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * Interface để trích xuất thông tin user từ các OAuth2 provider khác nhau
 */
public interface OAuth2UserInfoExtractor {
    String getEmail(OAuth2User oauth2User);
    String getName(OAuth2User oauth2User);
    String getProviderId(OAuth2User oauth2User);
    AuthProvider getProvider();
}