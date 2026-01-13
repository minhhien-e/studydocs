package studydocs.user.infrastructure.repository;

import studydocs.user.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMongoSpringRepository extends MongoRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}