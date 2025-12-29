package studydocs.notification.infrastructure.adapter.repository.fcm;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.FcmToken;
import studydocs.notification.domain.repository.FcmTokenRepository;
import studydocs.notification.infrastructure.mapper.FcmTokenMapper;
import studydocs.notification.infrastructure.persistence.entity.FcmTokenEntity;
import studydocs.notification.infrastructure.persistence.repository.FcmTokenMongoRepository;

@Repository
public class FcmTokenWriteAdapter extends AbstractAggregateMongoEntityRepository<FcmToken, FcmTokenEntity> implements FcmTokenRepository {
    private final FcmTokenMongoRepository fcmTokenMongoRepository;

    public FcmTokenWriteAdapter(MongoHelper mongoHelper, DomainEventSerializer domainEventSerializer, MongoTemplate mongoTemplate, FcmTokenMongoRepository fcmTokenMongoRepository) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
        this.fcmTokenMongoRepository = fcmTokenMongoRepository;
    }

    @Override
    public Class<FcmTokenEntity> getEntityClass() {
        return FcmTokenEntity.class;
    }

    @Override
    public FcmToken toDomainEntity(FcmTokenEntity entity) {
        return FcmTokenMapper.toDomain(entity);
    }

    @Override
    public void updateEntity(FcmTokenEntity snapshot, FcmToken domainEntity) {
        FcmTokenMapper.updateEntity(snapshot, domainEntity);
    }

    @Override
    public boolean existsByValue(String token) {
        return fcmTokenMongoRepository.existsByValue(token);
    }

    @Override
    public void deleteByValue(String token) {
        fcmTokenMongoRepository.deleteByValue(token);
    }

    @Override
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> childClass) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {

    }
}
