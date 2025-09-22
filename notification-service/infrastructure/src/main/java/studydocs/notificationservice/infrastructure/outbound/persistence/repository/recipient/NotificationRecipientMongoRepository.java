package studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.RecipientDocument;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientMongoRepository extends MongoRepository<RecipientDocument, UUID> {
    int countByRecipientIdAndReadIsFalseAndDeletedAtIsNull(UUID recipientId);

    Optional<RecipientDocument> findByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);

    List<RecipientDocument> findAllByRecipientIdAndRead(UUID recipientId, boolean read);

}
