package com.example.authservice.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginLocalRequest {

    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
}
