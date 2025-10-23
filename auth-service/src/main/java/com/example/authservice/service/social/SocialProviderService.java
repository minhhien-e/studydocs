package com.example.authservice.service.social;

import com.example.authservice.model.dto.social.SocialUserInfo;
import com.example.authservice.model.enums.AuthProvider;

public interface SocialProviderService {
    /**
     * Exchange authorization code lấy access token và sau đó lấy thông tin user
     */
    SocialUserInfo getUserInfoFromCode(String authorizationCode, String redirectUri);

    /**
     * Trả về provider type mà service này hỗ trợ
     */
    AuthProvider getProvider();
}