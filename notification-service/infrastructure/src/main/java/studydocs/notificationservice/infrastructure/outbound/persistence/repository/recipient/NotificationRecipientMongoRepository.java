package studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationRecipientDocument;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientMongoRepository extends MongoRepository<NotificationRecipientDocument, UUID> {
    boolean existsByRecipientIdAndReadIsFalse(UUID recipientId);

    int countByRecipientIdAndReadIsFalseAndDeletedAtIsNull(UUID recipientId);

    Optional<NotificationRecipientDocument> findByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);
}
