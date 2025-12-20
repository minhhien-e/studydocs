package studydocs.notification.infrastructure.adapter.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;
import studydocs.notification.application.port.out.repository.UserQueries;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueries {
    private final RemoteUserServicePort remoteUserServicePort;

    @Override
    public UserProjection getById(UUID id) {
        return remoteUserServicePort.getById(id);
    }
}
