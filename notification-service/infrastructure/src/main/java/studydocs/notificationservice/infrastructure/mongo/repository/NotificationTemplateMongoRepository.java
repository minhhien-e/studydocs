package studydocs.notificationservice.infrastructure.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationTemplateDocument;

import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateMongoRepository extends MongoRepository<NotificationTemplateDocument, UUID> {
    Optional<NotificationTemplateDocument> findByName(String name);
}
