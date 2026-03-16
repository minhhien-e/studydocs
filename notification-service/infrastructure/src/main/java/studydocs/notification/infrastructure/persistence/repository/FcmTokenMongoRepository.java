package studydocs.notification.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.infrastructure.persistence.entity.FcmTokenEntity;

import java.util.UUID;

public interface FcmTokenMongoRepository extends MongoRepository<FcmTokenEntity, UUID> {
    boolean existsByValue(String value);

    void deleteByValue(String token);
}
