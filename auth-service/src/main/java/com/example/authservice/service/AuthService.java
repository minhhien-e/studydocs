package com.example.authservice.service;

import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterRequest;
import com.example.authservice.model.dto.request.SocialLoginRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;

public interface AuthService {
    
    /**
     * Đăng ký tài khoản mới bằng email và mật khẩu
     * @param request Thông tin đăng ký từ client
     * @return UserResponse chứa thông tin user đã đăng ký
     */
    UserResponse registerLocal(RegisterRequest request);

    /**
     * Đăng nhập
     * @param request Thông tin đăng nhập
     * @return TokenResponse chứa access token và refresh token
     */
//     TokenResponse login(LoginRequest request);

//     /**
//      * Đăng nhập bằng tài khoản social
//      * @param request Thông tin đăng nhập social
//      * @return TokenResponse chứa authorization token và refresh token
//      */
//     TokenResponse socialLogin(SocialLoginRequest request);

//     /**
//      * Làm mới token
//      * @param refreshToken Refresh token cũ
//      * @return TokenResponse chứa access token và refresh token mới
//      */
//     TokenResponse refreshToken(String refreshToken);

//     /**
//      * Xác thực email
//      * @param token Token xác thực từ email
//      * @return true nếu xác thực thành công
//      */
//     boolean verifyEmail(String token);

//     /**
//      * Kiểm tra email đã tồn tại chưa
//      * @param email Email cần kiểm tra
//      * @return true nếu email đã tồn tại
//      */
//     boolean isEmailExists(String email);

//     /**
//      * Đăng xuất
//      * @param token Access token cần vô hiệu hóa
//      */
//     void logout(String token);

//     /**
//      * Lấy thông tin người dùng hiện tại
//      * @return UserResponse chứa thông tin user
//      */
//     UserResponse getCurrentUser();
} 