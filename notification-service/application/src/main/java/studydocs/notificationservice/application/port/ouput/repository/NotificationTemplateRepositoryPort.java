package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.domain.entities.NotificationTemplate;

import java.util.Optional;

public interface NotificationTemplateRepositoryPort {
    void save(NotificationTemplate notificationTemplate);

    Optional<NotificationTemplate> findByName(String name);
}
