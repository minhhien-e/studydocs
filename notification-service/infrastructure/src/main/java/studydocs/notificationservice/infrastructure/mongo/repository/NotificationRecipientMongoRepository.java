package studydocs.notificationservice.infrastructure.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationRecipientDocument;

import java.util.UUID;

public interface NotificationRecipientMongoRepository extends MongoRepository<NotificationRecipientDocument, UUID> {
    boolean existsByRecipientIdAndReadIsFalse(UUID recipientId);

    boolean existsByRecipientIdAndNotificationIdAndReadIsFalse(UUID recipientId, UUID notificationId);

    boolean existsByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);
}
