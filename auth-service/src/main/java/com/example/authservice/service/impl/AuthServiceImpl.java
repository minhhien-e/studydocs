package com.example.authservice.service.impl;

import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterRequest;
import com.example.authservice.model.dto.request.SocialLoginRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.exception.AuthenticationException;
import com.example.authservice.exception.AuthErrorCode;
import com.example.authservice.exception.ValidationException;
import com.example.authservice.model.entity.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO: Thêm JwtTokenProvider
    // private final JwtTokenProvider tokenProvider;e

    @Override
    @Transactional
    public UserResponse registerLocal(RegisterRequest request) {
        log.debug("Bắt đầu đăng ký user mới với email: {}", request.getEmail());

        // 1. Validate email format
        if (!isValidEmail(request.getEmail())) {
            log.warn("Email không hợp lệ: {}", request.getEmail());
            throw new ValidationException(
                    AuthErrorCode.INVALID_EMAIL_FORMAT,
                    "Email không đúng định dạng");
        }
         // 2. Validate password
        if (!isValidPassword(request.getPassword())) {
            log.warn("Password không đủ mạnh cho email: {}", request.getEmail());
            throw new ValidationException(
                    AuthErrorCode.WEAK_PASSWORD,
                    "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
        }
        // 3. Kiểm tra password matching
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.warn("Password không khớp cho email: {}", request.getEmail());
            throw new ValidationException(
                    AuthErrorCode.PASSWORD_MISMATCH,
                    "Mật khẩu xác nhận không khớp");
        }
        // 4. Kiểm tra email đã tồn tại
        if (isEmailExists(request.getEmail())) {
            log.warn("Email đã tồn tại: {}", request.getEmail());
            throw new ValidationException(
                    AuthErrorCode.EMAIL_EXISTS,
                    "Email đã được sử dụng");
        }

 
        // 5. Kiểm tra username đã tồn tại
        if (isUserNameExists(request.getUserName())) {
            log.warn("Username đã tồn tại: {}", request.getUserName());
            throw new ValidationException(
                    AuthErrorCode.USERNAME_EXISTS,
                    "Username đã được sử dụng");
        }
       
     
        try {
            // Tạo user mới
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setProvider(User.AuthProvider.LOCAL);
            user.setStatus(User.UserStatus.ACTIVE);

            // Lưu user vào database
            user = userRepository.save(user);
            log.info("Đã tạo user mới thành công với ID: {}", user.getId());

            // Gửi email xác thực
            sendVerificationEmail(user);

            // Convert và trả về response
            return convertToUserResponse(user);

        } catch (Exception e) {
            log.error("Lỗi khi đăng ký user: {}", e.getMessage(), e);
            throw new AuthenticationException(
                    AuthErrorCode.REGISTRATION_FAILED,
                    "Có lỗi xảy ra khi đăng ký tài khoản");
        }
    }

    // @Override
    // public TokenResponse login(LoginRequest request) {
    //     log.debug("Xử lý đăng nhập cho email: {}", request.getEmail());

    //     // Tìm user theo email
    //     User user = userRepository.findByEmail(request.getEmail());
    //     if (user == null) {
    //         log.warn("Không tìm thấy user với email: {}", request.getEmail());
    //         throw new AuthenticationException(
    //                 AuthErrorCode.INVALID_CREDENTIALS,
    //                 "Email hoặc mật khẩu không chính xác");
    //     }

    //     // Kiểm tra password
    //     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    //         log.warn("Mật khẩu không chính xác cho email: {}", request.getEmail());
    //         throw new AuthenticationException(
    //                 AuthErrorCode.INVALID_CREDENTIALS,
    //                 "Email hoặc mật khẩu không chính xác");
    //     }

    //     // Kiểm tra trạng thái tài khoản
    //     if (user.getStatus() != User.UserStatus.ACTIVE) {
    //         log.warn("Tài khoản không active: {}", request.getEmail());
    //         throw new AuthenticationException(
    //                 AuthErrorCode.ACCOUNT_LOCKED,
    //                 "Tài khoản đã bị khóa hoặc chưa kích hoạt");
    //     }

    //     // Tạo và trả về token
    //     return createTokenResponse(user, request.isRememberMe());
    // }

    // @Override
    // public TokenResponse socialLogin(SocialLoginRequest request) {
    //     log.debug("Xử lý đăng nhập social với provider: {}", request.getProvider());

    //     // TODO: Implement social login
    //     throw new UnsupportedOperationException("Social login chưa được hỗ trợ");
    // }

    // @Override
    // public TokenResponse refreshToken(String refreshToken) {
    //     log.debug("Xử lý refresh token");

    //     // TODO: Implement refresh token
    //     throw new UnsupportedOperationException("Refresh token chưa được hỗ trợ");
    // }

    // @Override
    // public boolean verifyEmail(String token) {
    //     log.debug("Xử lý xác thực email với token: {}", token);

    //     // TODO: Implement email verification
    //     return false;
    // }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // @Override
    // public void logout(String token) {
    //     log.debug("Xử lý đăng xuất");

    //     // TODO: Implement logout - invalidate token
    //     SecurityContextHolder.clearContext();
    // }

    // @Override
    // public UserResponse getCurrentUser() {
    //     log.debug("Lấy thông tin user hiện tại");

    //     // TODO: Implement get current user
    //     throw new UnsupportedOperationException("Chức năng chưa được hỗ trợ");
    // }

    // Helper methods
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty() || email.length() > 255) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8 
        || password.length() > 500) {
            return false;
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean isUserNameExists(String userName) {
        return userRepository.existsByUserName(userName);
    }

    private void sendVerificationEmail(User user) {
        // TODO: Implement email sending logic
        log.info("Gửi email xác thực cho user: {}", user.getEmail());
        //gọi api khác để xác thực email
    }

    // private TokenResponse createTokenResponse(User user, boolean rememberMe) {
    //     // TODO: Implement JWT token generation
    //     return TokenResponse.builder()
    //             .tokenType("Bearer")
    //             .expiresIn(rememberMe ? 604800 : 3600) // 7 days : 1 hour
    //             .build();
    // }

    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .provider(user.getProvider())
                .status(user.getStatus())
                .roles(user.getRoles() != null ? user.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toSet())
                        : Collections.emptySet())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}