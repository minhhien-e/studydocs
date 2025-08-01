package studydocs.notificationservice.infrastructure.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationRecipientDocument;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientMongoRepository extends MongoRepository<NotificationRecipientDocument, UUID> {
    boolean existsByRecipientIdAndIsReadIsFalse(UUID recipientId);

    int countByRecipientIdAndReadIsTrueAndDeletedIsFalse(UUID recipientId, UUID notificationId);

    Optional<NotificationRecipientDocument> findByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);
}
