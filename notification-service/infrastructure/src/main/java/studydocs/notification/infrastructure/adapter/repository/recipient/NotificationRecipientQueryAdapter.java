package studydocs.notification.infrastructure.adapter.repository.recipient;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;
import studydocs.notification.domain.exception.recipient.NotificationRecipientNotFoundException;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationRecipientMongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Repository
@RequiredArgsConstructor
public class NotificationRecipientQueryAdapter implements NotificationRecipientQueries {
    private final NotificationRecipientMongoRepository mongoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Integer countUnread(UUID recipientId) {
        return mongoRepository.countByRecipientIdAndIsRead(recipientId, false);
    }

    @Override
    public Long countByRecipientId(UUID recipientId, boolean deleted) {
        return deleted ? mongoRepository.countByRecipientIdAndDeletedAtIsNotNull(recipientId)
                : mongoRepository.countByRecipientIdAndDeletedAtIsNull(recipientId);
    }

    @Override
    public List<NotificationRecipientProjection> getByRecipientId(UUID recipientId, boolean deleted, LocalDateTime lastSeenReceiveAt, int limit) {
        MatchOperation matchRecipient = Aggregation.match(Criteria.where("recipientId").is(recipientId)
                .andOperator(deleted ? Criteria.where("deletedAt").ne(null) : Criteria.where("deletedAt").isNull(),
                        Criteria.where("receivedAt").lt(lastSeenReceiveAt)));
        LookupOperation lookupOperation = Aggregation.lookup("notifications", "notificationId", "_id", "notification");
        LimitOperation limitOperation = Aggregation.limit(limit);
        SortOperation sortReceiveAt = Aggregation.sort(Sort.Direction.DESC, "receivedAt");
        UnwindOperation unwindOperation = Aggregation.unwind("notification");
        Aggregation aggregation = Aggregation.newAggregation(matchRecipient, sortReceiveAt, limitOperation, lookupOperation, unwindOperation);
        return mongoTemplate.aggregate(aggregation, "notification_recipients", NotificationRecipientProjection.class)
                .getMappedResults();
    }

    @Override
    public NotificationRecipientProjection getById(UUID id) {
        return mongoRepository.findById(id).map(
                        NotificationRecipientMapper::toProjection
                )
                .orElseThrow(() -> new NotificationRecipientNotFoundException(id));
    }

    @Override
    public List<UUID> getUnreadNotificationIdsByRecipientId(
            UUID recipientId,
            int batchSize,
            LocalDateTime lastSeenReceivedAt
    ) {
        return findNotificationIds(
                recipientId,
                batchSize,
                lastSeenReceivedAt,
                c -> c.and("isRead").is(false)
                        .and("deletedAt").is(null)
        );
    }

    private List<UUID> findNotificationIds(
            UUID recipientId,
            int batchSize,
            LocalDateTime lastSeenReceivedAt,
            Consumer<Criteria> extraCriteria
    ) {
        Criteria criteria = Criteria.where("recipientId").is(recipientId)
                .and("receivedAt").lt(lastSeenReceivedAt);

        extraCriteria.accept(criteria);

        Query query = new Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "receivedAt"))
                .limit(batchSize);

        query.fields().include("notificationId");

        return mongoTemplate.find(
                        query,
                        NotificationRecipientEntity.class,
                        "notification_recipients"
                ).stream()
                .map(NotificationRecipientEntity::getNotificationId)
                .toList();
    }

}

