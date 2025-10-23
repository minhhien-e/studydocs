package com.example.authservicev2.service.impl;

import com.example.authservicev2.domain.model.entities.Role;
import com.example.authservicev2.domain.model.entities.User;
import com.example.authservicev2.domain.model.entities.User_roles;
import com.example.authservicev2.domain.model.request.LocalRegisterRequest;
import com.example.authservicev2.domain.model.request.LocalLoginRequest;
import com.example.authservicev2.domain.model.request.LoginRequest;
import com.example.authservicev2.domain.model.request.RegisterRequest;
import com.example.authservicev2.domain.model.response.TokenResponse;
import com.example.authservicev2.domain.model.response.UserResponse;
import com.example.authservicev2.domain.repository.RoleRepository;
import com.example.authservicev2.domain.repository.UserRepository;
import com.example.authservicev2.domain.repository.UserRolesRepository;
import com.example.authservicev2.exception.CustomExceptions;
import com.example.authservicev2.service.interfaces.AuthService;
import com.example.authservicev2.util.oauth.OAuthClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.RS256;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final OAuthClientFactory oAuthClientFactory;
    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final RoleRepository  roleRepository;


// đã chạy oke
    @Override
    public UserResponse registerLocal(LocalRegisterRequest registerRequest) {
        // Validate username
        if (!isValidUsername(registerRequest.getUsername())) {
            throw new CustomExceptions.ValidationException("Username không hợp lệ...");
        }

        // Validate password
        if (!isValidPassword(registerRequest.getPassword())) {
            throw new CustomExceptions.ValidationException("Password không hợp lệ...");
        }

        // Check username tồn tại
        if (usernameExists(registerRequest.getUsername())) {
            throw new CustomExceptions.ValidationException("Username đã tồn tại...");
        }

        // Check email tồn tại
        if (emailExists(registerRequest.getEmail())) {
            throw new CustomExceptions.ValidationException("Email đã tồn tại...");
        }

        // Tạo user mới
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        // Lưu vào DB
        userRepository.save(user);

        assignDefaultRoleToUser(user);
        // Trả response

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        return userResponse;
    }

    @Override
    public UserResponse registerSocial(RegisterRequest registerRequest) {
        var client = oAuthClientFactory.get(registerRequest.getProvider());
        String accessToken = client.exchangeCodeForAccessToken(
                registerRequest.getAuthorizationCode(),
                registerRequest.getRedirectUri()
        );
        var info = client.getUserInfo(accessToken);

        if (info.getProviderUserId() == null || info.getProviderUserId().isBlank()) {
            throw new CustomExceptions.ValidationException("Provider user ID is missing");
        }

        // Tìm theo id, nếu có thì trả về; nếu chưa thì tạo mới
        var existing = userRepository.findByProviderId(info.getProviderUserId());
        User user = existing.orElseGet(User::new);
        user.setEmail(info.getEmail());
        user.setUserName(info.getName());
        user.setProvider(registerRequest.getProvider());
        user.setProviderId(info.getProviderUserId());

        assignDefaultRoleToUser(user);
        // Mật khẩu có thể để null cho tài khoản social
        userRepository.save(user);

        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUserName());
        res.setEmail(user.getEmail());
        return res;
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        var client = oAuthClientFactory.get(loginRequest.getProvider());
        String accessToken = client.exchangeCodeForAccessToken(
                loginRequest.getAuthorizationCode(),
                loginRequest.getRedirectUri()
        );

        var info = client.getUserInfo(accessToken);

        var user = userRepository.findByProviderId(info.getProviderUserId())
                .orElseThrow(() -> new CustomExceptions.ValidationException("User không tồn tại"));

        var now = java.time.Instant.now();
        long expiresIn = 3600; // 1h
        var claims = JwtClaimsSet.builder()
                .issuer("auth-service-v2")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.getId().toString())
                .claim("uid", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getUserName())
                .claim("roles", userRolesRepository.findRolesByUserId(user.getId()).stream().map(Role::getName).toList())
                .claim("provider", user.getProvider() != null ? user.getProvider().name() : null)
                .build();

        var headers = JwsHeader.with(RS256).build();
        String jwt = jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();

        String refreshToken = generateRefreshToken();
//        saveRefreshToken(user.getId(), refreshToken, now.plus(30, ChronoUnit.DAYS));


        TokenResponse res = new TokenResponse();
        res.setAccess_token(jwt);
        res.setRefresh_token(refreshToken);
        res.setToken_type("Bearer");
        res.setExpires_in((int) expiresIn);
        res.setScope("openid profile email");
        // refresh_token, id_token có thể bổ sung sau nếu cần
        return res;
    }

    @Override
    public TokenResponse loginLocal(LocalLoginRequest request) {
        var user = userRepository.findByUserName(request.getUsername())
                .orElseThrow(() -> new CustomExceptions.ValidationException("User không tồn tại"));

        if (user.getPassword() == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomExceptions.ValidationException("Sai thông tin đăng nhập");
        }

        var now = java.time.Instant.now();
        long expiresIn = 3600;
        var claims = JwtClaimsSet.builder()
                .issuer("auth-service-v2")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.getId().toString())
                .claim("uid", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getUserName())
                .claim("roles", userRolesRepository.findRolesByUserId(user.getId()).stream().map(Role::getName).toList())
                .claim("provider", "user.getProvider()")
                .build();

        var headers = JwsHeader.with(RS256).build();
        String jwt = jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();

        String refreshToken = generateRefreshToken();
        TokenResponse res = new TokenResponse();
        res.setAccess_token(jwt);
        res.setRefresh_token(refreshToken);
        res.setToken_type("Bearer");
        res.setExpires_in((int) expiresIn);
        res.setScope("openid profile email");
        return res;
    }

    @Override
    public TokenResponse refreshToken(TokenResponse tokenResponse) {
        if (tokenResponse == null || tokenResponse.getRefresh_token() == null || tokenResponse.getRefresh_token().isBlank()) {
            throw new CustomExceptions.ValidationException("Refresh token is required");
        }

       RestTemplate rt = new RestTemplate();

       HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("my-client", "my-secret"); // khớp với seed client

       MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("refresh_token", tokenResponse.getRefresh_token());

        HttpEntity<MultiValueMap<String, String>> req =
                new HttpEntity<>(form, headers);

        var resp = rt.postForEntity("http://localhost:8080/oauth2/token", req, java.util.Map.class);
        var body = resp.getBody();
        if (body == null || !resp.getStatusCode().is2xxSuccessful()) {
            throw new CustomExceptions.ValidationException("Không lấy được access token mới");
        }

        TokenResponse res = new TokenResponse();
        res.setAccess_token((String) body.get("access_token"));
        res.setToken_type((String) body.get("token_type"));
        Object exp = body.get("expires_in");
        if (exp instanceof Number n) res.setExpires_in(n.intValue());
        res.setScope((String) body.get("scope"));
        if (body.containsKey("refresh_token")) res.setRefresh_token((String) body.get("refresh_token")); // rotation có thể trả mới
        if (body.containsKey("id_token")) res.setId_token((String) body.get("id_token"));
        return res;
    }


    /// /////////////////////////////////////////////////////////
    /**
     * Kiểm tra tính hợp lệ của username và password.
     * - Username: Bắt đầu bằng chữ cái, dài từ 3 đến 20 ký tự, chỉ chứa chữ cái, số và dấu gạch dưới.
     * - Password: Dài từ 6 đến 20 ký tự, chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt.
     */
    public boolean isValidUsername(String username) {
        // Kiểm tra độ dài của username
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        // Kiểm tra ký tự đầu tiên
        char firstChar = username.charAt(0);
        if (!Character.isLetter(firstChar)) {
            return false;
        }

        // Kiểm tra các ký tự còn lại
        for (int i = 1; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    /**
     * Kiểm tra tính hợp lệ của password.
     * - Password: Dài từ 6 đến 20 ký tự, chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt.
     */
    public boolean isValidPassword(String password) {
        // Kiểm tra độ dài của password
        if (password.length() < 6 || password.length() > 20) {
            return false;
        }

        // Kiểm tra ít nhất một chữ cái viết hoa, một chữ cái viết thường, một số và một ký tự đặc biệt
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!@#$%^&*()-_=+[]{}|;:',.<>?/~`".indexOf(c) >= 0) {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUserName(username);
    }
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private void assignDefaultRoleToUser(User user) {
        try {
            Role defaultRole = roleRepository.findByName("NORMAL")
                    .orElseThrow(() -> new CustomExceptions.ValidationException("Default role not found"));
    
            // Đảm bảo user đã có id
            if (user.getId() == null) {
                userRepository.save(user);
            }
    
            User_roles userRole = new User_roles(user, defaultRole, user); // createdBy = user

            userRolesRepository.save(userRole);

        } catch (Exception e) {
            throw new CustomExceptions.ValidationException("Error assigning default role to user: " + e.getMessage());
        }
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString();
    }
}
