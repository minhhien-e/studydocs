package studydocs.notification.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;

import java.util.UUID;

public interface NotificationMongoDataRepository extends MongoRepository<NotificationEntity, UUID> {
}
