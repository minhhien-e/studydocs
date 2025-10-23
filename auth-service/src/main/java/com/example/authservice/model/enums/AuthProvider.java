package com.example.authservice.model.enums;
// Enum cho provider
public enum AuthProvider {
    FACEBOOK, GOOGLE, GITHUB, LOCAL;
    // Bạn có thể thêm các provider khác nếu cần
    // Ví dụ: APPLE, MICROSOFT, TWITTER, v.v.

    public static AuthProvider fromString(String provider) {
        if (provider == null) return null;
        try {
            return AuthProvider.valueOf(provider.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported provider: " + provider);
        }
    }
}
