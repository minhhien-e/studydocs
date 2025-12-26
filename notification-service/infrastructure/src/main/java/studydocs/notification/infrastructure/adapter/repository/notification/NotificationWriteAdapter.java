package studydocs.notification.infrastructure.adapter.repository.notification;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.exception.ResourceNotFoundException;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;

import java.util.UUID;

@Repository
public class NotificationWriteAdapter
        extends AbstractAggregateMongoEntityRepository<Notification, NotificationEntity>
        implements NotificationRepository {

    public NotificationWriteAdapter(DomainEventSerializer domainEventSerializer,
                                    MongoTemplate mongoTemplate,
                                    MongoHelper mongoHelper) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
    }

    @Override
    public Notification getById(UUID id) {
        try {
            return super.getById(id);
        } catch (ResourceNotFoundException e) {
            throw new NotificationNotFoundException(id);
        }
    }

    @Override
    public Class<NotificationEntity> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public Notification toDomainEntity(NotificationEntity notificationEntity) {
        return NotificationMapper.toDomain(notificationEntity);
    }

    @Override
    public void updateEntity(NotificationEntity snapshot, Notification domain) {
        NotificationMapper.updateEntity(snapshot, domain);
    }

    @Override
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> childClass) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {

    }
}
