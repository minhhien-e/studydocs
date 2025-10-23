package com.example.authservicev2.domain.model.request;

import com.example.authservicev2.domain.enums.Provider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "Provider không được bỏ trống")
    private Provider provider;

    @NotBlank(message = "Authorization code không được bỏ trống")
    private String authorizationCode;

    @NotBlank(message = "Redirect URI không được bỏ trống")
    private String redirectUri;

}
