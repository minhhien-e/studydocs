package com.example.authservice.service.social;

import com.example.authservice.model.enums.AuthProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2UserInfoExtractorFactory {
    private final Map<AuthProvider, OAuth2UserInfoExtractor> extractors;

    public OAuth2UserInfoExtractorFactory(List<OAuth2UserInfoExtractor> extractorList) {
        extractors = new HashMap<>();
        extractorList.forEach(extractor -> 
            extractors.put(extractor.getProvider(), extractor));
    }

    public OAuth2UserInfoExtractor getExtractor(AuthProvider provider) {
        OAuth2UserInfoExtractor extractor = extractors.get(provider);
        if (extractor == null) {
            throw new IllegalArgumentException("Không hỗ trợ provider: " + provider);
        }
        return extractor;
    }
}