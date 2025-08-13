package studydocs.notificationservice.infrastructure.outbound.persistence.repository.notification.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.domain.repository.NotificationRepositoryPort;
import studydocs.notificationservice.domain.entity.Notification;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.NotificationMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.notification.NotificationMongoRepository;

@Repository
@RequiredArgsConstructor
public class NotificationRepository implements NotificationRepositoryPort {
    private final NotificationMongoRepository repository;

    @Override
    public void save(Notification notification) {
        NotificationDocument document = NotificationMapper.toDocument(notification);
        repository.save(document);
    }
}
