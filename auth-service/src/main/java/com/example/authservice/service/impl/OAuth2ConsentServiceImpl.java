package com.example.authservice.service.impl;

import com.example.authservice.service.OAuth2ConsentService;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OAuth2ConsentServiceImpl implements OAuth2ConsentService {

    private static final Map<String, Map<String, String>> SCOPE_DESCRIPTIONS = new HashMap<>();

    static {
        // Mô tả cho từng scope
        SCOPE_DESCRIPTIONS.put(OidcScopes.OPENID, Map.of(
            "description", "Xác thực danh tính của bạn",
            "details", "Cho phép ứng dụng xác thực người dùng"
        ));
        
        SCOPE_DESCRIPTIONS.put(OidcScopes.PROFILE, Map.of(
            "description", "Xem thông tin cá nhân cơ bản",
            "details", "Bao gồm tên, ảnh đại diện và thông tin công khai khác"
        ));
        
        SCOPE_DESCRIPTIONS.put(OidcScopes.EMAIL, Map.of(
            "description", "Xem địa chỉ email",
            "details", "Cho phép ứng dụng xem địa chỉ email của bạn"
        ));
        
        SCOPE_DESCRIPTIONS.put("write", Map.of(
            "description", "Chỉnh sửa dữ liệu",
            "details", "Cho phép ứng dụng tạo và cập nhật dữ liệu thay mặt bạn"
        ));
        
        SCOPE_DESCRIPTIONS.put("read", Map.of(
            "description", "Đọc dữ liệu",
            "details", "Cho phép ứng dụng đọc dữ liệu của bạn"
        ));
    }

    @Override
    public List<Map<String, String>> getScopeDescriptions(String scopesString) {
        List<Map<String, String>> descriptions = new ArrayList<>();
        String[] scopes = scopesString.split(" ");

        for (String scope : scopes) {
            Map<String, String> description = SCOPE_DESCRIPTIONS.getOrDefault(
                scope,
                Map.of(
                    "description", scope,
                    "details", "Quyền truy cập bổ sung"
                )
            );

            Map<String, String> scopeInfo = new HashMap<>();
            scopeInfo.put("scope", scope);
            scopeInfo.put("description", description.get("description"));
            scopeInfo.put("details", description.get("details"));
            
            descriptions.add(scopeInfo);
        }

        return descriptions;
    }

    @Override
    public void saveUserConsent(String principalName, String clientId, Set<String> scopes) {
        // TODO: Lưu thông tin consent vào database
        // Có thể lưu để sau này hiển thị lại hoặc cho phép user thu hồi quyền
    }
}