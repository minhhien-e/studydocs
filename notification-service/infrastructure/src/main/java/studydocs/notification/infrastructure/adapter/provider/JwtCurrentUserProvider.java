package studydocs.notification.infrastructure.adapter.provider;

import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.provider.CurrentUserProvider;

import java.util.UUID;

@Component
public  class JwtCurrentUserProvider implements CurrentUserProvider {
    public UUID getCurrentUserId() {
//        var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return UUID.fromString(jwt.getSubject());
        return UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
    }
}
