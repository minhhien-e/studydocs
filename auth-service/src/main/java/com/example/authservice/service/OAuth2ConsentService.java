package com.example.authservice.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OAuth2ConsentService {
    /**
     * Lấy mô tả chi tiết cho các scopes
     * @param scopesString Chuỗi các scopes cách nhau bởi dấu cách
     * @return Danh sách mô tả cho từng scope
     */
    List<Map<String, String>> getScopeDescriptions(String scopesString);

    /**
     * Lưu thông tin consent của user
     * @param principalName Username của user
     * @param clientId ID của client application
     * @param scopes Các scopes được chấp thuận
     */
    void saveUserConsent(String principalName, String clientId, Set<String> scopes);
}