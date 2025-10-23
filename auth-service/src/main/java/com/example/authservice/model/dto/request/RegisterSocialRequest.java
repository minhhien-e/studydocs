package com.example.authservice.model.dto.request;

import com.example.authservice.model.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSocialRequest {
    @NotNull(message = "Provider không được để trống")
    private AuthProvider provider;

    @NotBlank(message = "Authorization code không được để trống")
    private String authorizationCode;

    @NotBlank(message = "Redirect URI không được để trống")
    private String redirectUri;
}