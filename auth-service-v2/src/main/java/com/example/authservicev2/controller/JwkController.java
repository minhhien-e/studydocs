package com.example.authservicev2.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwkController {

    private final JWKSource<SecurityContext> jwkSource;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> jwkSet() {
        try {
            // Tạo một JWKSelector để lấy tất cả keys
            var selector = new com.nimbusds.jose.jwk.JWKSelector(
                    new com.nimbusds.jose.jwk.JWKMatcher.Builder().build()
            );

            // Lấy JWK Set từ JWKSource
            var jwks = jwkSource.get(selector, null);
            JWKSet jwkSet = new JWKSet(jwks);

            return jwkSet.toJSONObject();
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy JWK Set: " + e.getMessage(), e);
        }
    }
}