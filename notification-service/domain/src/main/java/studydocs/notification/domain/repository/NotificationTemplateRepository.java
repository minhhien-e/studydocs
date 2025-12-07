package studydocs.notification.domain.repository;

import io.github.domain.repository.DomainEntityRepository;
import studydocs.notification.domain.entity.NotificationTemplate;

import java.util.List;
import java.util.UUID;

public interface NotificationTemplateRepository extends DomainEntityRepository<NotificationTemplate> {
    NotificationTemplate getById(UUID id);

    boolean existsById(UUID id);

    boolean existsByName(String name);
}
