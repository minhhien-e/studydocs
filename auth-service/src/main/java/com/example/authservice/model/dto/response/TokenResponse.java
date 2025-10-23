package com.example.authservice.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String idToken;       // For OpenID Connect
    private Long expiresIn;
    private String scope;
}