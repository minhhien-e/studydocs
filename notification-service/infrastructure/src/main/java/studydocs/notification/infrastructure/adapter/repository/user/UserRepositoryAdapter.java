package studydocs.notification.infrastructure.adapter.repository.user;

import org.springframework.stereotype.Repository;
import studydocs.notification.domain.repository.UserRepository;

import java.util.List;
import java.util.UUID;
@Repository
public class UserRepositoryAdapter implements UserRepository {
    @Override
    public boolean existsById(UUID id) {
        return true;
    }

    @Override
    public boolean existsAllByIdIn(List<UUID> ids) {
        return true;
    }
}
