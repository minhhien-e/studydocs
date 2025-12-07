package studydocs.notification.infrastructure.persistence.repository.notification.impl;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.entity.NotificationRecipient;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificationMongoRepositoryAdapter extends AbstractAggregateMongoRepository<Notification, NotificationEntity>
        implements NotificationRepository {

    public NotificationMongoRepositoryAdapter(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    public Notification getById(UUID id, List<UUID> recipientIds) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(id));
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("notification_recipients")
                .pipeline(
                        Aggregation.match(
                                Criteria.where("notificationId").is(id)
                        ),
                        Aggregation.match(
                                Criteria.where("recipientId").in(recipientIds)
                        )
                ).as("notificationRecipients");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, lookupOperation);
        var notificationDocuments = mongoTemplate.aggregate(aggregation, "notifications", NotificationEntity.class).getMappedResults();
        return notificationDocuments.stream().map(NotificationMapper::toDomain).findFirst().orElse(null);
    }

    @Override
    public List<Notification> getByRecipientId(UUID recipientId, List<UUID> notificationIds) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").in(notificationIds));
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("notification_recipients")
                .pipeline(
                        Aggregation.match(
                                Criteria.where("recipientId").is(recipientId)
                        )
                ).as("notificationRecipients");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, lookupOperation);
        var notificationDocuments = mongoTemplate.aggregate(aggregation, "notifications", NotificationEntity.class).getMappedResults();
        return notificationDocuments.stream().map(NotificationMapper::toDomain).toList();
    }

    @Override
    public Class<?> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public Class<?> getChildEntityClass(AggregateChild childName) {
        Class<?> entityClass = null;
        if (childName instanceof NotificationRecipient) {
            entityClass = NotificationRecipientEntity.class;
        }
        return entityClass;
    }

    @Override
    public NotificationEntity toEntity(Notification aggregate) {
        return NotificationMapper.toEntity(aggregate);
    }

    @Override
    protected Object toChildEntity(DomainEntity entity) {
        if (entity instanceof NotificationRecipient) {
            return NotificationRecipientMapper.toEntity((NotificationRecipient) entity);
        }
        return null;
    }
}
