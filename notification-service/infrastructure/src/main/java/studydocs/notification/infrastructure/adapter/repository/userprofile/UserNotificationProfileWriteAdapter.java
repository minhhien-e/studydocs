package studydocs.notification.infrastructure.adapter.repository.userprofile;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
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
        extends AbstractAggregateMongoEntityRepository<UserNotificationProfile, UserNotificationProfileEntity>
        implements UserNotificationProfileRepository {
    
    private final UserNotificationProfileMongoRepository mongoRepository;

    public UserNotificationProfileWriteAdapter(
            UserNotificationProfileMongoRepository mongoRepository,
            MongoTemplate mongoTemplate,
            MongoHelper mongoHelper,
            DomainEventSerializer domainEventSerializer
    ) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
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
    public Class<UserNotificationProfileEntity> getEntityClass() {
        return UserNotificationProfileEntity.class;
    }
    
    @Override
    public UserNotificationProfile toDomainEntity(UserNotificationProfileEntity entity) {
        return UserNotificationProfileMapper.toDomain(entity);
    }

    @Override
    public void updateEntity(UserNotificationProfileEntity snapshot, UserNotificationProfile domain) {
        UserNotificationProfileMapper.updateEntity(snapshot, domain);
    }

    @Override
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> childClass) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {
    }
}
