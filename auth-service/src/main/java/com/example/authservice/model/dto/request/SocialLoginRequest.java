package com.example.authservice.model.dto.request;

import com.example.authservice.model.entity.User;
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
public class SocialLoginRequest {
    
    @NotBlank(message = "Access token không được để trống")
    private String accessToken;

    @NotNull(message = "Provider không được để trống")
    private User.AuthProvider provider;
} 