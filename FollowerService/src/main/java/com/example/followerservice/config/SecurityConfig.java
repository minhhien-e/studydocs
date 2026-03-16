package com.example.followerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Cấu hình Spring Security với JWT từ auth service
 * Extract permissions từ JWT và convert thành authorities
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Cho phép dùng @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationEntryPoint authenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler
    ) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF cho REST API
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless JWT
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (không cần authentication) - nếu có
                        // .requestMatchers("/api/v1/follows/public").permitAll()

                        // Tất cả endpoints khác cần authentication
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new ApiResponseAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new ApiResponseAccessDeniedHandler(objectMapper);
    }

    /**
     * Custom converter để extract permissions và roles từ JWT claims
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return converter;
    }

    /**
     * Custom converter để lấy permissions từ JWT claims "permissions"
     * và roles từ "roles" claim
     * 
     * Note: Không đánh dấu @Bean để tránh Spring đăng ký vào ConversionService
     */
    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            // Lấy permissions từ claim "permissions"
            Object permissions = jwt.getClaim("permissions");
            if (permissions instanceof List<?> permList) {
                List<GrantedAuthority> permissionAuthorities = permList.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .map(perm -> new SimpleGrantedAuthority("SCOPE_" + perm))
                        .collect(Collectors.toList());
                authorities.addAll(permissionAuthorities);
            }

            // Lấy roles từ claim "roles" (optional)
            Object roles = jwt.getClaim("roles");
            if (roles instanceof List<?> roleList) {
                List<GrantedAuthority> roleAuthorities = roleList.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
                authorities.addAll(roleAuthorities);
            }

            return authorities;
        };
    }
}
