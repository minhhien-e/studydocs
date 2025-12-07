package studydocs.notification.domain.repository;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    boolean existsById(UUID id);
    boolean existsAllByIdIn(List<UUID> ids);
}
