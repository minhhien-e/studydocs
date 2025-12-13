package studydocs.notification.infrastructure.adapter.repository.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.NotificationProjection;
import studydocs.notification.application.port.out.repository.NotificationRepository;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class NotificationMongoReadAdapter implements NotificationRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Integer countUnread(UUID recipientId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("recipientId").is(recipientId)
                .and("isRead").is(false));

        return Math.toIntExact(mongoTemplate.count(query, NotificationRecipientEntity.class));
    }

    @Override
    public List<NotificationProjection> getByRecipientId(UUID recipientId, boolean deleted, LocalDateTime receivedAt, int limit) {
        List<AggregationOperation> operations = new ArrayList<>();

        // Lookup
        LookupOperation lookupRecipients = LookupOperation.newLookup()
                .from("notification_recipients")
                .pipeline(
                        Aggregation.match(Criteria.where("recipientId").is(recipientId)),
                        !deleted ? Aggregation.match(Criteria.where("deletedAt").isNull())
                                : Aggregation.match(Criteria.where("deletedAt").ne(null)),
                        Aggregation.match(Criteria.where("receivedAt").lt(receivedAt)),
                        Aggregation.sort(Sort.by(Sort.Direction.DESC, "receivedAt"))
                ).as("notificationRecipients");
        operations.add(lookupRecipients);


        // Lookup template
        LookupOperation lookupTemplate = LookupOperation.newLookup()
                .from("notification_templates")
                .localField("templateId")
                .foreignField("_id")
                .as("notificationTemplate");
        operations.add(lookupTemplate);

        UnwindOperation unwindTemplate = Aggregation.unwind("notificationTemplate", true);
        operations.add(unwindTemplate);

        // Limit
        operations.add(Aggregation.limit(limit));

        // Build aggregation
        Aggregation aggregation = Aggregation.newAggregation(operations);

        var notifications = mongoTemplate.aggregate(aggregation, "notifications", NotificationEntity.class)
                .getMappedResults();
        return notifications.stream().map(NotificationMapper::toProjection).toList();
    }

    @Override
    public List<UUID> getUnreadNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenReceivedAt) {
        return getNotificationIdsByRecipientId(recipientId, batchSize, lastSeenReceivedAt, true, false);
    }

    @Override
    public List<UUID> getDeletedNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenReceivedAt) {
        return getNotificationIdsByRecipientId(recipientId, batchSize, lastSeenReceivedAt, null, true);
    }

    private List<UUID> getNotificationIdsByRecipientId(
            UUID recipientId,
            int batchSize,
            LocalDateTime lastSeenReceivedAt,
            Boolean unreadOnly,   // true = isRead = false, null = không filter
            Boolean deletedOnly   // true = deleted, false = not deleted, null = không filter
    ) {
        List<AggregationOperation> operations = new ArrayList<>();

        // Match recipientId
        operations.add(Aggregation.match(Criteria.where("recipientId").is(recipientId)));

        // Match deleted
        if (deletedOnly != null) {
            if (deletedOnly) {
                operations.add(Aggregation.match(Criteria.where("deletedAt").ne(null)));
            } else {
                operations.add(Aggregation.match(Criteria.where("deletedAt").isNull()));
            }
        }

        // Match unread
        if (Boolean.TRUE.equals(unreadOnly)) {
            operations.add(Aggregation.match(Criteria.where("isRead").is(false)));
        }

        // Match createdAt < cursor
        operations.add(Aggregation.match(Criteria.where("receivedAt").lt(lastSeenReceivedAt)));

        // Limit batch
        operations.add(Aggregation.limit(batchSize));

        // Project notificationId
        operations.add(Aggregation.project("notificationId"));

        // Build aggregation
        Aggregation aggregation = Aggregation.newAggregation(operations);

        // Execute and map to UUID
        return mongoTemplate.aggregate(aggregation, "notification_recipients", NotificationRecipientEntity.class)
                .getMappedResults()
                .stream()
                .map(NotificationRecipientEntity::getNotificationId)
                .toList();
    }


}
