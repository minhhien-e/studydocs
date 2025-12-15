package studydocs.notification.infrastructure.adapter.repository.user;

import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.application.port.out.repository.UserQueries;

import java.util.UUID;

@Repository
public class UserReadAdapter implements UserQueries {
    @Override
    public UserProjection getById(UUID id) {
        return null;
    }
}
