package studydocs.notification.infrastructure.adapter.repository.userprofile;

import io.github.infrastructure.mongo.repository.base.AbstractEntityMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.UserNotificationProfile;
import studydocs.notification.domain.exception.userprofile.UserNotificationProfileNotFoundException;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;
import studydocs.notification.infrastructure.mapper.UserNotificationProfileMapper;
import studydocs.notification.infrastructure.persistence.entity.UserNotificationProfileEntity;
import studydocs.notification.infrastructure.persistence.repository.UserNotificationProfileMongoRepository;

import java.util.List;
import java.util.UUID;


@Repository
public class UserNotificationProfileWriteAdapter 
        extends AbstractEntityMongoRepository<UserNotificationProfile, UserNotificationProfileEntity>
        implements UserNotificationProfileRepository {
    
    private final UserNotificationProfileMongoRepository mongoRepository;
    
    public UserNotificationProfileWriteAdapter(
            MongoTemplate mongoTemplate,
            UserNotificationProfileMongoRepository mongoRepository
    ) {
        super(mongoTemplate);
        this.mongoRepository = mongoRepository;
    }
    
    @Override
    public UserNotificationProfile getByUserId(UUID userId) {
        return mongoRepository.findByUserId(userId)
                .map(UserNotificationProfileMapper::toDomain)
                .orElseThrow(() -> new UserNotificationProfileNotFoundException(userId));
    }
    
    @Override
    public boolean existsByUserId(UUID userId) {
        return mongoRepository.existsByUserId(userId);
    }

    @Override
    public boolean existsAllByUserIdIn(List<UUID> userIds) {
        return mongoRepository.existsAllByUserIdIn(userIds);
    }

    @Override
    public Class<?> getEntityClass() {
        return UserNotificationProfileEntity.class;
    }
    
    @Override
    public UserNotificationProfileEntity toEntity(UserNotificationProfile domainEntity) {
        return UserNotificationProfileMapper.toEntity(domainEntity);
    }
}
