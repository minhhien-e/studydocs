package studydocs.notification.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.notification.domain.aggregate.NotificationTemplate;

import java.util.UUID;

public interface NotificationTemplateRepository extends AggregateRootWriter<NotificationTemplate> {
    boolean existsById(UUID id);

    boolean existsByName(String name);
}
