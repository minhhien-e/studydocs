package com.studydocs.modules.user.service;

import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.TokenResponseDto;

/**
 * Interface định nghĩa dịch vụ xác thực tài khoản (Đăng ký, Đăng nhập, Làm mới Token).
 *
 * @author StudyDocs Team
 * @since 1.0.0
 */
public interface AuthService {

    /**
     * Đăng ký tài khoản người dùng mới.
     *
     * @param request DTO chứa email, password, họ tên và thông tin trường học
     * @return {@link TokenResponseDto} chứa JWT token và thông tin user vừa tạo
     */
    TokenResponseDto register(LoginRequest.Register request);

    /**
     * Đăng nhập tài khoản bằng Email hoặc Username.
     *
     * @param request DTO chứa credential (email/username + password)
     * @return {@link TokenResponseDto} chứa JWT Access Token và Refresh Token
     */
    TokenResponseDto login(LoginRequest.Login request);

    /**
     * Cấp lại Access Token mới bằng Refresh Token còn hạn.
     *
     * @param request DTO chứa Refresh Token
     * @return {@link TokenResponseDto} mới
     */
    TokenResponseDto refreshToken(LoginRequest.RefreshToken request);
}
