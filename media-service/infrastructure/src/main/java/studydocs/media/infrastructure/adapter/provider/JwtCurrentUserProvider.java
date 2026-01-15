package studydocs.media.infrastructure.adapter.provider;

import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import studydocs.media.application.port.in.provider.CurrentUserProviderPort;

import java.util.UUID;

@Component
public class JwtCurrentUserProvider implements CurrentUserProviderPort {
    @Override
    public UUID getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            try {
                return UUID.fromString(subject);
            } catch (IllegalArgumentException e) {
                if (jwt.hasClaim("id")) {
                    return UUID.fromString(jwt.getClaimAsString("id"));
                }
            }
        }

        throw new IllegalStateException("Current user is not authenticated");
    }
}
