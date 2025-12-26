package studydocs.notification.infrastructure.adapter.repository.recipient;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.exception.ResourceNotFoundException;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.exception.recipient.NotificationRecipientNotFoundException;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationRecipientMongoRepository;

import java.util.UUID;

@Repository
public class NotificationRecipientWriteAdapter
        extends AbstractAggregateMongoEntityRepository<NotificationRecipient, NotificationRecipientEntity>
        implements NotificationRecipientRepository {

    private final NotificationRecipientMongoRepository mongoRepository;

    public NotificationRecipientWriteAdapter(
            NotificationRecipientMongoRepository mongoRepository,
            DomainEventSerializer domainEventSerializer,
            MongoTemplate mongoTemplate,
            MongoHelper mongoHelper
    ) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Class<NotificationRecipientEntity> getEntityClass() {
        return NotificationRecipientEntity.class;
    }

    @Override
    public NotificationRecipient toDomainEntity(NotificationRecipientEntity entity) {
        return NotificationRecipientMapper.toDomain(entity);
    }

    @Override
    public void updateEntity(NotificationRecipientEntity snapshot, NotificationRecipient domain) {
        NotificationRecipientMapper.updateEntity(snapshot, domain);
    }

    @Override
    public NotificationRecipient getById(UUID id) {
        try {
            return super.getById(id);
        } catch (ResourceNotFoundException e) {
            throw new NotificationRecipientNotFoundException(id);
        }
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
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> childClass) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {
    }
}
