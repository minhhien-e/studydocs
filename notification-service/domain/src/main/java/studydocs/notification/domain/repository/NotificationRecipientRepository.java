package studydocs.notification.domain.repository;

import io.github.domain.repository.DomainEntityRepository;
import studydocs.notification.domain.aggregate.NotificationRecipient;

import java.util.List;
import java.util.UUID;

public interface NotificationRecipientRepository extends DomainEntityRepository<NotificationRecipient> {
    NotificationRecipient getByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);
    List<NotificationRecipient> getByRecipientId(UUID recipientId);
    boolean deleteByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);
}
