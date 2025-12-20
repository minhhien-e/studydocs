package studydocs.notification.infrastructure.adapter.repository.fcm;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoEntityWriter;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.FcmToken;
import studydocs.notification.domain.repository.FcmTokenRepository;
import studydocs.notification.infrastructure.mapper.FcmTokenMapper;
import studydocs.notification.infrastructure.persistence.entity.FcmTokenEntity;
import studydocs.notification.infrastructure.persistence.repository.FcmTokenMongoRepository;

@Repository
public class FcmTokenWriteAdapter extends AbstractAggregateMongoRepository<FcmToken, FcmTokenEntity> implements FcmTokenRepository {
    private final FcmTokenMongoRepository fcmTokenMongoRepository;
    public FcmTokenWriteAdapter(MongoEntityWriter mongoEntityWriter, DomainEventSerializer domainEventSerializer, FcmTokenMongoRepository fcmTokenMongoRepository) {
        super(mongoEntityWriter, domainEventSerializer);
        this.fcmTokenMongoRepository = fcmTokenMongoRepository;
    }

    @Override
    public Class<?> getEntityClass() {
        return FcmTokenEntity.class;
    }

    @Override
    public FcmTokenEntity toEntity(FcmToken domainEntity) {
        return FcmTokenMapper.toEntity(domainEntity);
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
    protected Class<?> getChildEntityClass(AggregateChild child) {
        return null;
    }

    @Override
    protected MongoEntity toChildEntity(DomainEntity entity) {
        return null;
    }
}
