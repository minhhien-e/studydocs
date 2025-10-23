package com.example.authservice.service.social;

import com.example.authservice.model.entity.User;
import com.example.authservice.model.enums.AuthProvider;
import com.example.authservice.model.enums.UserStatus;
import com.example.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        
        try {
            return processOAuth2User(userRequest, oauth2User);
        } catch (Exception ex) {
            log.error("OAuth2 authentication failed", ex);
            throw new OAuth2AuthenticationException(ex.getMessage());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        // Xác định provider (Google, Facebook, etc)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = AuthProvider.valueOf(registrationId.toUpperCase());

        // Lấy thông tin từ OAuth2 provider
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = getEmail(attributes, provider);
        String name = getName(attributes, provider);

        // Tìm user trong database bằng email
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;

        if (userOptional.isPresent()) {
            // Nếu user đã tồn tại, update thông tin
            user = userOptional.get();
            if (!provider.equals(user.getProvider())) {
                throw new OAuth2AuthenticationException(
                    "You're signed up with " + user.getProvider() + ". Please use that to login."
                );
            }
            user = updateExistingUser(user, attributes, provider);
        } else {
            // Nếu user chưa tồn tại, tạo mới
            user = registerNewUser(attributes, provider);
        }

        return oauth2User;
    }

    private User registerNewUser(Map<String, Object> attributes, AuthProvider provider) {
        User user = new User();
        
        // Set thông tin cơ bản
        user.setProvider(provider);
        user.setProviderId(getProviderId(attributes, provider));
        user.setEmail(getEmail(attributes, provider));
        user.setUserName(generateUsername(attributes, provider));
        user.setStatus(UserStatus.ACTIVE);

        log.info("Registering new OAuth2 user: {}", user.getEmail());
        return userRepository.save(user);
    }

    private User updateExistingUser(User user, Map<String, Object> attributes, AuthProvider provider) {
        // Update thông tin nếu cần
        String name = getName(attributes, provider);
        if (name != null && !name.equals(user.getUserName())) {
            user.setUserName(name);
        }
        
        log.info("Updating OAuth2 user: {}", user.getEmail());
        return userRepository.save(user);
    }

    /**
     * Lấy email từ OAuth2 attributes tùy theo provider
     */
    private String getEmail(Map<String, Object> attributes, AuthProvider provider) {
        switch (provider) {
            case GOOGLE:
                return (String) attributes.get("email");
            case FACEBOOK:
                return (String) attributes.get("email");
            case GITHUB:
                return (String) attributes.get("email");
            default:
                throw new OAuth2AuthenticationException("Unsupported provider: " + provider);
        }
    }

    /**
     * Lấy tên từ OAuth2 attributes tùy theo provider
     */
    private String getName(Map<String, Object> attributes, AuthProvider provider) {
        switch (provider) {
            case GOOGLE:
                return (String) attributes.get("name");
            case FACEBOOK:
                return (String) attributes.get("name");
            case GITHUB:
                return (String) attributes.get("login"); // GitHub uses 'login' as username
            default:
                throw new OAuth2AuthenticationException("Unsupported provider: " + provider);
        }
    }

    /**
     * Lấy provider ID từ OAuth2 attributes
     */
    private String getProviderId(Map<String, Object> attributes, AuthProvider provider) {
        switch (provider) {
            case GOOGLE:
                return (String) attributes.get("sub");
            case FACEBOOK:
                return (String) attributes.get("id");
            case GITHUB:
                return String.valueOf(attributes.get("id"));
            default:
                throw new OAuth2AuthenticationException("Unsupported provider: " + provider);
        }
    }

    /**
     * Generate username cho user mới
     * Ưu tiên:
     * 1. Tên từ provider nếu có
     * 2. Email prefix nếu không có tên
     * 3. Provider + ID nếu không có email
     */
    private String generateUsername(Map<String, Object> attributes, AuthProvider provider) {
        // Thử lấy tên từ provider
        String name = getName(attributes, provider);
        if (name != null && !name.isEmpty()) {
            String baseUsername = name.replaceAll("\\s+", "").toLowerCase();
            return ensureUniqueUsername(baseUsername);
        }

        // Nếu không có tên, thử dùng email prefix
        String email = getEmail(attributes, provider);
        if (email != null && !email.isEmpty()) {
            String baseUsername = email.split("@")[0];
            return ensureUniqueUsername(baseUsername);
        }

        // Nếu không có email, dùng provider + ID
        String providerId = getProviderId(attributes, provider);
        String baseUsername = provider.toString().toLowerCase() + "_" + providerId;
        return ensureUniqueUsername(baseUsername);
    }

    /**
     * Đảm bảo username là duy nhất bằng cách thêm số nếu cần
     */
    private String ensureUniqueUsername(String baseUsername) {
        String username = baseUsername;
        int counter = 1;
        
        while (userRepository.existsByUserName(username)) {
            username = baseUsername + counter++;
        }
        
        return username;
    }
}