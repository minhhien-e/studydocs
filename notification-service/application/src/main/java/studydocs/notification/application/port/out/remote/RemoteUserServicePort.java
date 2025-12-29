package studydocs.notification.application.port.out.remote;

import studydocs.notification.application.dto.projection.UserProjection;

import java.util.UUID;

public interface RemoteUserServicePort {
    UserProjection getById(UUID id);
}
