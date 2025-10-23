package com.example.authservice.model.dto.social;

import com.example.authservice.model.enums.AuthProvider;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocialUserInfo {
    private String id;          // ID từ social provider
    private String email;       // Email
    private String name;        // Tên hiển thị
    private String imageUrl;    // URL ảnh đại diện
    private AuthProvider provider; // Loại provider (GOOGLE, FACEBOOK, etc)
    private boolean emailVerified; // Email đã được verify chưa
}