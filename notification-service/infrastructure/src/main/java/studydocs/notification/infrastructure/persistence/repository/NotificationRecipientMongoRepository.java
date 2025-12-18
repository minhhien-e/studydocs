package studydocs.notification.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientMongoRepository extends MongoRepository<NotificationRecipientEntity, UUID> {
    Optional<NotificationRecipientEntity> findByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);

    List<NotificationRecipientEntity> findByRecipientId(UUID recipientId);

    long deleteByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId);

    Integer countByRecipientIdAndIsRead(UUID recipientId, Boolean isRead);

    Long countByRecipientIdAndDeletedAtIsNull(UUID recipientId);

    Long countByRecipientIdAndDeletedAtIsNotNull(UUID recipientId);
}
