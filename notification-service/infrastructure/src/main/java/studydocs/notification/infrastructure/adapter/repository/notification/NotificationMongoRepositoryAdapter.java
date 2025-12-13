package studydocs.notification.infrastructure.adapter.repository.notification;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationMongoDataRepository;

import java.util.UUID;

@Repository
public class NotificationMongoRepositoryAdapter
        extends AbstractAggregateMongoRepository<Notification, NotificationEntity>
        implements NotificationRepository {
    private final NotificationMongoDataRepository mongoDataRepository;

    public NotificationMongoRepositoryAdapter(MongoTemplate mongoTemplate, NotificationMongoDataRepository mongoDataRepository) {
        super(mongoTemplate);
        this.mongoDataRepository = mongoDataRepository;
    }

    @Override
    public Notification getById(UUID id) {
        return mongoDataRepository.findById(id).map(NotificationMapper::toDomain).orElseThrow(()->new NotificationNotFoundException(id));
    }

    @Override
    public Class<?> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public NotificationEntity toEntity(Notification aggregate) {
        return NotificationMapper.toEntity(aggregate);
    }

    @Override
    protected Class<?> getChildEntityClass(AggregateChild child) {
        return null;
    }

    @Override
    protected Object toChildEntity(DomainEntity entity) {
        return null;
    }
}
