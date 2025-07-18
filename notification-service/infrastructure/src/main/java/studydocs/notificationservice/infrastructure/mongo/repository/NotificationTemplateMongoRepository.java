package studydocs.notificationservice.infrastructure.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationTemplateDocument;

import java.util.Optional;

public interface NotificationTemplateMongoRepository extends MongoRepository<NotificationTemplateDocument, String> {
    Optional<NotificationTemplateDocument> findByName(String name);
}
