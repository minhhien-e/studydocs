package studydocs.notification.infrastructure.adapter.repository.userprofile;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoEntityWriter;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
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
        extends AbstractAggregateMongoRepository<UserNotificationProfile, UserNotificationProfileEntity>
        implements UserNotificationProfileRepository {
    
    private final UserNotificationProfileMongoRepository mongoRepository;

    public UserNotificationProfileWriteAdapter(
            UserNotificationProfileMongoRepository mongoRepository,
            MongoEntityWriter mongoEntityWriter,
            DomainEventSerializer domainEventSerializer
    ) {
        super(mongoEntityWriter,domainEventSerializer);
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

    @Override
    protected Class<?> getChildEntityClass(AggregateChild aggregateChild) {
        return null;
    }

    @Override
    protected MongoEntity toChildEntity(DomainEntity domainEntity) {
        return null;
    }
}
