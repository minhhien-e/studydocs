package com.example.authservice.service.impl;

import com.example.authservice.exception.AuthErrorCode;
import com.example.authservice.exception.AuthenticationException;
import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterLocalRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.model.entity.User;
import com.example.authservice.model.enums.UserStatus;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;

import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RegisteredClientRepository clientRepository;
    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<OAuth2Token> tokenGenerator;

    @Override
    @Transactional
    public TokenResponse registerLocal(RegisterLocalRequest request) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthenticationException(AuthErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // Tạo user mới
        User user = User.builder()
            .email(request.getEmail())
            .userName(request.getEmail().split("@")[0]) // Tạo username từ email
            .password(passwordEncoder.encode(request.getPassword()))
            .status(UserStatus.ACTIVE)
            .build();

        userRepository.save(user);
        log.info("Local user registered successfully: {}", user.getEmail());

        // Authenticate và tạo OAuth2 authorization
        return authenticateAndCreateAuthorization(user.getEmail(), request.getPassword());
    }

    @Override
    public TokenResponse loginLocal(LoginRequest request) {
        return authenticateAndCreateAuthorization(request.getUsername(), request.getPassword());
    }

    @Override
    public TokenResponse refreshToken(String refreshToken, String clientId) {
        // Tìm client
        RegisteredClient client = clientRepository.findByClientId(clientId);
        if (client == null) {
            throw new AuthenticationException(AuthErrorCode.INVALID_CLIENT);
        }

        // Tìm authorization bằng refresh token
        OAuth2Authorization authorization = authorizationService.findByToken(
             refreshToken,
            OAuth2TokenType.REFRESH_TOKEN
        );

        if (authorization == null) {
            throw new AuthenticationException(AuthErrorCode.INVALID_REFRESH_TOKEN);
        }

        // Lấy authentication từ authorization
        Authentication principal = authorization.getAttribute(Principal.class.getName());
        if (principal == null) {
            throw new AuthenticationException(AuthErrorCode.INVALID_TOKEN);
        }

        // Tạo access token mới
        OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder()
            .registeredClient(client)
            .principal(principal)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .authorizedScopes(authorization.getAuthorizedScopes())
            .tokenType(OAuth2TokenType.ACCESS_TOKEN)
            .authorizationGrant(principal)
            .build();

        OAuth2Token accessToken = tokenGenerator.generate(tokenContext);
        if (accessToken == null) {
            throw new AuthenticationException(AuthErrorCode.TOKEN_GENERATION_FAILED);
        }

        // Cập nhật authorization với token mới
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.from(authorization);
        authorizationBuilder.token(accessToken);
        OAuth2Authorization newAuthorization = authorizationBuilder.build();
        authorizationService.save(newAuthorization);

        return TokenResponse.builder()
            .accessToken(accessToken.getTokenValue())
            .tokenType("Bearer")
            .expiresIn(client.getTokenSettings().getAccessTokenTimeToLive().toSeconds())
            .refreshToken(refreshToken)
            .build();
    }

    @Override
    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new AuthenticationException(AuthErrorCode.USER_NOT_FOUND));

        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .userName(user.getUserName())
            .status(user.getStatus())
            .provider(user.getProvider())
            .build();
    }

    private TokenResponse authenticateAndCreateAuthorization(String username, String password) {
        try {
            // Authenticate với Spring Security
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // Tìm client
            RegisteredClient client = clientRepository.findByClientId("web-client");

            // Tạo OAuth2 authorization builder
            OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(client)
                .principalName(authentication.getName())
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .attribute(Principal.class.getName(), authentication);

            // Tạo access token context
            OAuth2TokenContext accessTokenContext = DefaultOAuth2TokenContext.builder()
                .registeredClient(client)
                .principal(authentication)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrant(authentication)
                .build();

            // Tạo refresh token context
            OAuth2TokenContext refreshTokenContext = DefaultOAuth2TokenContext.builder()
                .registeredClient(client)
                .principal(authentication)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .tokenType(OAuth2TokenType.REFRESH_TOKEN)
                .authorizationGrant(authentication)
                .build();

            // Generate tokens
            OAuth2Token accessToken = tokenGenerator.generate(accessTokenContext);
            OAuth2Token refreshToken = tokenGenerator.generate(refreshTokenContext);

            if (accessToken == null || refreshToken == null) {
                throw new AuthenticationException(AuthErrorCode.TOKEN_GENERATION_FAILED);
            }

            // Lưu authorization
            OAuth2Authorization authorization = authorizationBuilder
                .token(accessToken)
                .token(refreshToken)
                .build();
            authorizationService.save(authorization);

            return TokenResponse.builder()
                .accessToken(accessToken.getTokenValue())
                .refreshToken(refreshToken.getTokenValue())
                .tokenType("Bearer")
                .expiresIn(client.getTokenSettings().getAccessTokenTimeToLive().toSeconds())
                .build();

        } catch (Exception e) {
            log.error("Authentication failed", e);
            throw new AuthenticationException(AuthErrorCode.INVALID_CREDENTIALS);
        }
    }
}