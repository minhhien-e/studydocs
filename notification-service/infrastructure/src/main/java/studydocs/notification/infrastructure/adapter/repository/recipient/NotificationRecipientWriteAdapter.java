package studydocs.notification.infrastructure.adapter.repository.recipient;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoEntityWriter;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationRecipientMongoRepository;

import java.util.UUID;

@Repository
public class NotificationRecipientWriteAdapter
        extends AbstractAggregateMongoRepository<NotificationRecipient, NotificationRecipientEntity>
        implements NotificationRecipientRepository {

    private final NotificationRecipientMongoRepository mongoRepository;

    public NotificationRecipientWriteAdapter(
            NotificationRecipientMongoRepository mongoRepository,
            MongoEntityWriter mongoEntityWriter,
            DomainEventSerializer domainEventSerializer
    ) {
        super(mongoEntityWriter, domainEventSerializer);
        this.mongoRepository = mongoRepository;
    }

    @Override
    public NotificationRecipient getByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId) {
        return mongoRepository.findByNotificationIdAndRecipientId(notificationId, recipientId)
                .map(NotificationRecipientMapper::toDomain)
                .orElseThrow(() -> new NotificationNotFoundException(notificationId));
    }
    @Override
    public boolean existsByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId) {
        return mongoRepository.existsByNotificationIdAndRecipientId(notificationId, recipientId);
    }

    @Override
    public boolean deleteByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId) {
        return mongoRepository.deleteByNotificationIdAndRecipientId(notificationId, recipientId) > 0;
    }

    @Override
    public Class<?> getEntityClass() {
        return NotificationRecipientEntity.class;
    }

    @Override
    public NotificationRecipientEntity toEntity(NotificationRecipient aggregate) {
        return NotificationRecipientMapper.toEntity(aggregate);
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
