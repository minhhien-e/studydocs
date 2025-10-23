package com.example.authservice.service.social;

import com.example.authservice.model.enums.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuth2UserInfoExtractor implements OAuth2UserInfoExtractor {
    
    @Override
    public String getEmail(OAuth2User oauth2User) {
        return oauth2User.getAttribute("email");
    }

    @Override
    public String getName(OAuth2User oauth2User) {
        return oauth2User.getAttribute("name");
    }

    @Override
    public String getProviderId(OAuth2User oauth2User) {
        return oauth2User.getAttribute("sub");
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }
}