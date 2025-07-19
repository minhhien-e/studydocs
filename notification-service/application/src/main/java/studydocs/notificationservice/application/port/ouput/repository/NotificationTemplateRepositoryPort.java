package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.domain.entities.NotificationTemplate;

import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateRepositoryPort {
    void save(NotificationTemplate notificationTemplate);

    Optional<NotificationTemplate> findByName(String name);
    Optional<NotificationTemplate> findById(UUID id);
}
