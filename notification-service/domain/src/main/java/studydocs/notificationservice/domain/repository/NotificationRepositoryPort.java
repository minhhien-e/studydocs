package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.model.entity.Notification;

import java.util.UUID;

public interface NotificationRepositoryPort {
    void save(Notification notification);

    void delete(UUID notificationId);
}
