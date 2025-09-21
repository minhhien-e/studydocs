package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.model.entity.NotificationTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateRepositoryPort {
    void save(NotificationTemplate notificationTemplate);

    //region Find
    Optional<NotificationTemplate> findByName(String name);

    Optional<NotificationTemplate> findById(UUID id);

    //endregion
    //region Find All
    List<NotificationTemplate> findAll();

    List<NotificationTemplate> searchByName(String name);

    List<NotificationTemplate> findByChannel(String channel);

    //endregion
    //region Update
    long updateName(UUID id, String newName);

    long updateSubject(UUID id, String newSubject);

    long updateBody(UUID id, String newBody);

    long updateDescription(UUID id, String newDescription);
    //endregion
}
