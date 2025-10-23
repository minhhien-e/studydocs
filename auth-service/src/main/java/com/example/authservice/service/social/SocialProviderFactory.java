package com.example.authservice.service.social;

import com.example.authservice.exception.AuthenticationException;
import com.example.authservice.exception.AuthErrorCode;
import com.example.authservice.model.enums.AuthProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SocialProviderFactory {
    private final Map<AuthProvider, SocialProviderService> providers = new HashMap<>();

    public SocialProviderFactory(List<SocialProviderService> providerServices) {
        providerServices.forEach(service -> 
            providers.put(service.getProvider(), service));
    }

    public SocialProviderService getProvider(AuthProvider provider) {
        SocialProviderService service = providers.get(provider);
        if (service == null) {
            throw new AuthenticationException(AuthErrorCode.SOCIAL_PROVIDER_ERROR,
                "Unsupported provider: " + provider);
        }
        return service;
    }
}