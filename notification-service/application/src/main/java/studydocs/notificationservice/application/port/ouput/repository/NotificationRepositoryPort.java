package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.domain.entities.Notification;

public interface NotificationRepositoryPort {
    void save(Notification notification);

}
