package studydocs.notificationservice.infrastructure.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationDocument;

public interface NotificationMongoRepository extends MongoRepository<NotificationDocument, String> {
}
