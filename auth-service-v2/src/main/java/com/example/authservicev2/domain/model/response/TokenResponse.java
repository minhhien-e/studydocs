package com.example.authservicev2.domain.model.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;
    private String refresh_token;
    private String id_token;
}
