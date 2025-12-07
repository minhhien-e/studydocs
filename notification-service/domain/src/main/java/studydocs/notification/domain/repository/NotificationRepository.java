package studydocs.notification.domain.repository;

import io.github.domain.repository.DomainEntityRepository;
import studydocs.notification.domain.aggregate.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends DomainEntityRepository<Notification> {
    Notification getById(UUID id, List<UUID> recipientIds);
    List<Notification> getByRecipientId(UUID recipientId,List<UUID> notificationIds);
}
