package com.example.authservice.service.impl;

import com.example.authservice.exception.AuthenticationException;
import com.example.authservice.exception.AuthErrorCode;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.entity.User;
import com.example.authservice.model.enums.AuthProvider;
import com.example.authservice.model.enums.UserStatus;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.OAuth2AuthenticationService;
import com.example.authservice.service.social.OAuth2UserInfoExtractor;
import com.example.authservice.service.social.OAuth2UserInfoExtractorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2AuthenticationServiceImpl implements OAuth2AuthenticationService {

    private final UserRepository userRepository;
    private final OAuth2UserInfoExtractorFactory extractorFactory;
    private final RegisteredClientRepository clientRepository;
    private final OAuth2AuthorizationService authorizationService;

    @Override
    @Transactional
    public TokenResponse handleOAuth2Authentication(OAuth2AuthenticationToken authentication) {
        try {
            // Lấy thông tin provider
            AuthProvider provider = AuthProvider.valueOf(authentication.getAuthorizedClientRegistrationId().toUpperCase());
            OAuth2User oauth2User = authentication.getPrincipal();
            
            // Lấy extractor phù hợp với provider
            OAuth2UserInfoExtractor extractor = extractorFactory.getExtractor(provider);
            
            // Lấy email từ OAuth2 user
            String email = extractor.getEmail(oauth2User);
            if (email == null || email.isEmpty()) {
                throw new AuthenticationException(
                    AuthErrorCode.REGISTRATION_FAILED,
                    "Email không được cung cấp từ " + provider
                );
            }

            // Tìm hoặc tạo user
            User user = findOrCreateUser(oauth2User, extractor, provider);

            // Tạo OAuth2 authorization
            OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(
                    clientRepository.findByClientId("web-client")
                )
                .principalName(user.getEmail())
                .authorizationGrantType(authentication.getAuthorizedClientRegistrationId())
                .attribute("user", user)
                .build();

            authorizationService.save(authorization);

            log.info("OAuth2 authentication successful for user: {}", user.getEmail());

            return TokenResponse.builder()
                .accessToken(authorization.getAccessToken().getToken().getTokenValue())
                .refreshToken(authorization.getRefreshToken().getToken().getTokenValue())
                .tokenType("Bearer")
                .expiresIn(authorization.getAccessToken().getExpiresIn().toSeconds())
                .build();

        } catch (Exception e) {
            log.error("OAuth2 authentication failed", e);
            throw new AuthenticationException(
                AuthErrorCode.AUTHENTICATION_FAILED,
                "Đăng nhập thất bại: " + e.getMessage()
            );
        }
    }

    private User findOrCreateUser(
            OAuth2User oauth2User,
            OAuth2UserInfoExtractor extractor,
            AuthProvider provider) {
        
        String email = extractor.getEmail(oauth2User);
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return updateExistingUser(existingUser.get(), oauth2User, extractor, provider);
        }

        return createNewUser(oauth2User, extractor, provider);
    }

    private User updateExistingUser(
            User user,
            OAuth2User oauth2User,
            OAuth2UserInfoExtractor extractor,
            AuthProvider provider) {
        
        // Kiểm tra provider
        if (!provider.equals(user.getProvider())) {
            throw new AuthenticationException(
                AuthErrorCode.INVALID_PROVIDER,
                "Tài khoản đã được đăng ký với " + user.getProvider() + 
                ". Vui lòng sử dụng phương thức đăng nhập tương ứng."
            );
        }

        // Cập nhật thông tin nếu cần
        String name = extractor.getName(oauth2User);
        if (name != null && !name.equals(user.getUserName())) {
            user.setUserName(name);
        }

        return userRepository.save(user);
    }

    private User createNewUser(
            OAuth2User oauth2User,
            OAuth2UserInfoExtractor extractor,
            AuthProvider provider) {
        
        User user = new User();
        user.setEmail(extractor.getEmail(oauth2User));
        user.setProvider(provider);
        user.setProviderId(extractor.getProviderId(oauth2User));
        
        // Set username
        String name = extractor.getName(oauth2User);
        String username = name != null ? name : user.getEmail().split("@")[0];
        user.setUserName(ensureUniqueUsername(username));
        
        user.setStatus(UserStatus.ACTIVE);
        
        log.info("Creating new OAuth2 user: {}", user.getEmail());
        return userRepository.save(user);
    }

    private String ensureUniqueUsername(String baseUsername) {
        String username = baseUsername;
        int counter = 1;
        
        while (userRepository.existsByUserName(username)) {
            username = baseUsername + counter++;
        }
        
        return username;
    }
}