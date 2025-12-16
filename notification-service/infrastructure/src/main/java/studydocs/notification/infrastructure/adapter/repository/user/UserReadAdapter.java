package studydocs.notification.infrastructure.adapter.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;
import studydocs.notification.application.port.out.repository.UserQueries;
import studydocs.notification.infrastructure.mapper.UserMapper;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserReadAdapter implements UserQueries {
    private final RemoteUserServicePort remoteUserServicePort;

    @Override
    public UserProjection getById(UUID id) {
        return UserMapper.toProjection(remoteUserServicePort.getById(id));
    }
}
