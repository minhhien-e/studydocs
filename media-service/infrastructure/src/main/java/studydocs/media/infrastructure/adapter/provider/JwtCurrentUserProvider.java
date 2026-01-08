package studydocs.media.infrastructure.adapter.provider;

import org.springframework.stereotype.Component;
import studydocs.media.application.port.in.provider.CurrentUserProviderPort;

import java.util.UUID;

@Component
public class JwtCurrentUserProvider implements CurrentUserProviderPort {
    @Override
    public UUID getCurrentUserId() {
        // Mocked for now, similar to reference
        return UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
    }
}
