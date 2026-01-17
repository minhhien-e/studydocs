package studydocs.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Cấu hình Spring Security với JWT từ auth service
 * Extract permissions từ JWT và convert thành authorities
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Cho phép dùng @PreAuthorize
public class SecurityConfig {
        @Bean
        @ConditionalOnMissingBean(SecurityFilterChain.class)
        public SecurityFilterChain securityFilterChain(
                        HttpSecurity http,
                        AuthenticationEntryPoint authenticationEntryPoint,
                        AccessDeniedHandler accessDeniedHandler,
                        JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF cho REST API
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
                                // JWT
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/v1/internal/**", "/api/v1/reviews/public/**",
                                                                "/api/v1/reviews/internal/**")
                                                .permitAll()
                                                // Tất cả endpoints khác cần authentication
                                                .anyRequest().authenticated())
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(authenticationEntryPoint)
                                                .accessDeniedHandler(accessDeniedHandler))
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .jwt(jwt -> jwt
                                                                .jwtAuthenticationConverter(
                                                                                jwtAuthenticationConverter)));

                return http.build();
        }
}