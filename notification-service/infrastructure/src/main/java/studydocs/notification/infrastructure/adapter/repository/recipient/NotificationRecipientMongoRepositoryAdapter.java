package studydocs.notification.infrastructure.adapter.repository.recipient;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationRecipientMongoDataRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificationRecipientMongoRepositoryAdapter
        extends AbstractAggregateMongoRepository<NotificationRecipient, NotificationRecipientEntity>
        implements NotificationRecipientRepository {

    private final NotificationRecipientMongoDataRepository mongoRepository;

    public NotificationRecipientMongoRepositoryAdapter(
            MongoTemplate mongoTemplate,
            NotificationRecipientMongoDataRepository mongoRepository
    ) {
        super(mongoTemplate);
        this.mongoRepository = mongoRepository;
    }

    @Override
    public NotificationRecipient getByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId) {
        return mongoRepository.findByNotificationIdAndRecipientId(notificationId, recipientId)
                .map(NotificationRecipientMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<NotificationRecipient> getByRecipientId(UUID recipientId) {
        return mongoRepository.findByRecipientId(recipientId).stream()
                .map(NotificationRecipientMapper::toDomain)
                .toList();
    }

    @Override
    public boolean deleteByNotificationIdAndRecipientId(UUID notificationId, UUID recipientId) {
        return mongoRepository.deleteByNotificationIdAndRecipientId(notificationId,recipientId);
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
    protected Class<?> getChildEntityClass(AggregateChild child) {
        return null;
    }

    @Override
    protected Object toChildEntity(DomainEntity entity) {
        return null;
    }
}
