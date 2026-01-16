package com.example.demoauth.service;

import com.example.demoauth.domain.User;
import com.example.demoauth.exception.AuthErrorCodes;
import com.example.demoauth.exception.AuthException;
import com.example.demoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final StringRedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${app.otp.forgot-password.ttl-seconds:300}")
    private long otpTtlSeconds;

    @Value("${app.otp.forgot-password.resend-interval-seconds:60}")
    private long resendIntervalSeconds;

    @Value("${app.otp.forgot-password.max-attempts:5}")
    private int maxVerifyAttempts;

    private static final String OTP_KEY_PREFIX = "otp:forgot:";
    private static final String OTP_ATTEMPT_PREFIX = "otp:attempt:";

    public void generateAndSendOtp(String email) {
        // 1. Kiểm tra user tồn tại
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Email không tồn tại: {}", email);
                   return new AuthException(HttpStatus.NOT_FOUND, AuthErrorCodes.USER_NOT_FOUND, "Email không tồn tại trong hệ thống");
                });

        // 2. Rate limit: Kiểm tra TTL của key cũ để tránh spam
        String otpKey = OTP_KEY_PREFIX + email;
        Long expire = redisTemplate.getExpire(otpKey);
        if (expire != null && otpTtlSeconds - expire < resendIntervalSeconds) {
             throw new AuthException(HttpStatus.TOO_MANY_REQUESTS, AuthErrorCodes.OTP_RATE_LIMIT, "Vui lòng chờ giây lát trước khi gửi lại OTP");
        }

        // 3. Sinh OTP
        String otp = String.format("%06d", secureRandom.nextInt(1_000_000));

        // 4. Lưu Redis
        redisTemplate.opsForValue().set(otpKey, otp, Duration.ofSeconds(otpTtlSeconds));
        // Reset số lần thử sai
        redisTemplate.delete(OTP_ATTEMPT_PREFIX + email);

        log.info("Đã sinh OTP cho email: {} - Chờ notification service gọi lấy mã", email);
    }

    public String getOTP(String email) {
        String otpKey = OTP_KEY_PREFIX + email;
        return redisTemplate.opsForValue().get(otpKey);
    }

    @Transactional
    public void verifyAndResetPassword(String email, String otp, String newPassword) {
        log.info("Xác thực OTP và đổi mật khẩu cho email: {}", email);

        String otpKey = OTP_KEY_PREFIX + email;
        String attemptKey = OTP_ATTEMPT_PREFIX + email;

        // 1. Lấy OTP từ Redis
        String storedOtp = redisTemplate.opsForValue().get(otpKey);
        if (storedOtp == null) {
            throw new AuthException(HttpStatus.BAD_REQUEST, AuthErrorCodes.OTP_EXPIRED, "OTP đã hết hạn hoặc không tồn tại");
        }

        // 2. Check số lần thử sai
        String attemptsStr = redisTemplate.opsForValue().get(attemptKey);
        int attempts = attemptsStr == null ? 0 : Integer.parseInt(attemptsStr);
        if (attempts >= maxVerifyAttempts) {
            redisTemplate.delete(otpKey); // Xoá OTP đi
            throw new AuthException(HttpStatus.TOO_MANY_REQUESTS, AuthErrorCodes.OTP_MAX_ATTEMPTS, "Bạn đã nhập sai quá số lần cho phép");
        }

        // 3. Verify OTP
        if (!storedOtp.equals(otp)) {
            redisTemplate.opsForValue().increment(attemptKey);
            redisTemplate.expire(attemptKey, Duration.ofSeconds(otpTtlSeconds)); // Giữ TTL theo OTP
            throw new AuthException(HttpStatus.BAD_REQUEST, AuthErrorCodes.OTP_INVALID, "Mã OTP không chính xác");
        }

        // 4. Đổi mật khẩu
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException(HttpStatus.NOT_FOUND, AuthErrorCodes.USER_NOT_FOUND, "User không tìm thấy"));
        
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // 5. Cleanup
        redisTemplate.delete(otpKey);
        redisTemplate.delete(attemptKey);

        log.info("Đổi mật khẩu thành công cho user: {}", email);
    }
}
