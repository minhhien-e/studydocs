package com.studydocs.modules.user.service.impl;

import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.TokenResponseDto;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.entity.UserEntity;
import com.studydocs.modules.user.repository.UserRepository;
import com.studydocs.modules.user.service.AuthService;
import com.studydocs.modules.user.service.JwtTokenProvider;
import com.studydocs.modules.user.service.UserService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lớp triển khai dịch vụ đăng ký, đăng nhập và gia hạn phiên đăng nhập JWT.
 *
 * @author StudyDocs Team
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    @Transactional
    public TokenResponseDto register(LoginRequest.Register request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED, "Email is already registered");
        }

        // Tự động lấy prefix email làm username nếu người dùng không cung cấp
        String username = request.getUsername() != null ? request.getUsername() : request.getEmail().split("@")[0];

        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .username(username)
                .universityId(request.getUniversityId())
                .isPrivate(false)
                .build();

        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        UserDto userDto = userService.toUserDto(user);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getAccessTokenExpirationMs() / 1000)
                .user(userDto)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public TokenResponseDto login(LoginRequest.Login request) {
        // Cho phép tìm kiếm tài khoản linh hoạt theo Email hoặc Username
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .or(() -> userRepository.findByUsername(request.getEmail()))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIALS, "Invalid email/username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS, "Invalid email/username or password");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        UserDto userDto = userService.toUserDto(user);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getAccessTokenExpirationMs() / 1000)
                .user(userDto)
                .build();
    }

    @Override
    public TokenResponseDto refreshToken(LoginRequest.RefreshToken request) {
        if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
            throw new AppException(ErrorCode.INVALID_TOKEN, "Invalid or expired refresh token");
        }

        String userId = jwtTokenProvider.getUserIdFromToken(request.getRefreshToken());
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        UserDto userDto = userService.toUserDto(user);

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getAccessTokenExpirationMs() / 1000)
                .user(userDto)
                .build();
    }
}
