package studydocs.notificationservice.infrastructure.outbound.persistence.repository.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationDocument;

import java.util.UUID;

public interface NotificationMongoRepository extends MongoRepository<NotificationDocument, UUID> {
}
