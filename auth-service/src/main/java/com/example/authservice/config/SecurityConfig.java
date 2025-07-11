// src/main/java/com/example/authservice/config/SecurityConfig.java
package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Cấu hình mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình bảo mật cho các endpoint
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF vì chúng ta sẽ sử dụng JWT
            .authorizeHttpRequests(auth -> auth
                // Cho phép truy cập các endpoint đăng ký và đăng nhập
                .requestMatchers("/api/v1/auth/**").permitAll()
                // Yêu cầu xác thực cho tất cả các request khác
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}