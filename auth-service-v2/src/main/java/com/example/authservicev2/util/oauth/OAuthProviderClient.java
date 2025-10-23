package com.example.authservicev2.util.oauth;

import com.example.authservicev2.domain.enums.Provider;

public interface OAuthProviderClient {
    Provider supports();

    String exchangeCodeForAccessToken(String code, String redirectUri);

    SocialUserInfo getUserInfo(String accessToken);
}