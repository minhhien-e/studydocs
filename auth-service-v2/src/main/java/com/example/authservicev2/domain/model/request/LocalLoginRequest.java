package com.example.authservicev2.domain.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocalLoginRequest {
    @NotBlank(message = "Username không được bỏ trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
}


