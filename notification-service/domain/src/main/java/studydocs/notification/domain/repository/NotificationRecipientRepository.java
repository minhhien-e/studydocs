package studydocs.notification.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.notification.domain.aggregate.NotificationRecipient;

import java.util.UUID;

public interface NotificationRecipientRepository extends AggregateRootWriter<NotificationRecipient> {
    NotificationRecipient getByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);
    boolean deleteByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);
    boolean existsByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);
}
