package studydocs.notification.infrastructure.adapter.repository.user;

import studydocs.notification.application.dto.readmodel.UserReadModel;
import studydocs.notification.application.port.out.repository.UserQueries;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserReadAdapter implements UserQueries {
    @Override
    public UserReadModel getById(UUID id) {
        return null;
    }
}
