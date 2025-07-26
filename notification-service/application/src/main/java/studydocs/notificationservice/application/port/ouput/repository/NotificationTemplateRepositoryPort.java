package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.domain.entities.NotificationTemplate;

import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateRepositoryPort {
    void save(NotificationTemplate notificationTemplate);

    Optional<NotificationTemplate> findByName(String name);

    Optional<NotificationTemplate> findById(UUID id);

    long updateName(UUID id, String newName);

    long updateSubject(UUID id, String newSubject);

    long updateBody(UUID id, String newBody);

    long updateDescription(UUID id, String newDescription);
}
