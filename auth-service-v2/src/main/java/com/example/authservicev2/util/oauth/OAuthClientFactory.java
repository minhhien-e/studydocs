package com.example.authservicev2.util.oauth;

import com.example.authservicev2.domain.enums.Provider;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuthClientFactory {
    private final Map<Provider, OAuthProviderClient> registry = new EnumMap<>(Provider.class);

    public OAuthClientFactory(List<OAuthProviderClient> clients) {
        for (OAuthProviderClient c : clients) {
            registry.put(c.supports(), c);
        }
    }

    public OAuthProviderClient get(Provider provider) {
        OAuthProviderClient client = registry.get(provider);
        if (client == null) throw new IllegalArgumentException("Provider không được hỗ trợ: " + provider);
        return client;
    }
}