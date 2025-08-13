package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.entity.Notification;

public interface NotificationRepositoryPort {
    void save(Notification notification);

}
