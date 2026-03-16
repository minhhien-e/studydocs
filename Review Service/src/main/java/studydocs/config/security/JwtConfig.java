//package studydocs.config.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//
///**
// * Cấu hình JWT Decoder sử dụng JWKS từ auth service
// */
//@Configuration
//public class JwtConfig {
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    private String jwkSetUri;
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // NimbusJwtDecoder tự động fetch JWKS từ endpoint
//        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//    }
//}