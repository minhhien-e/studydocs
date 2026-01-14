package studydocs.user.interfaces.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtCurrentTokenProvider implements CurrentTokenProvider {
    @Override
    public String getCurrentToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getCredentials() == null) {
            return null;
        }
        return ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
//        return "";
    }
}
