package studydocs.notification.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserNotificationProfileMongoRepository
        extends MongoRepository<UserNotificationProfileEntity, UUID> {
    
    Optional<UserNotificationProfileEntity> findByUserId(UUID userId);
    
    boolean existsByUserId(UUID userId);

    boolean existsAllByUserIdIn(List<UUID> userIds);
}
