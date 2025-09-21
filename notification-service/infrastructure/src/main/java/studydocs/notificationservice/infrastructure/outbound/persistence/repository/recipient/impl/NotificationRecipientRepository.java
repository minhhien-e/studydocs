package studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.domain.model.entity.NotificationRecipient;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationRecipientDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.RecipientMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient.NotificationRecipientMongoRepository;
import studydocs.notificationservice.shared.exception.concrete.recipient.RecipientNotFoundException;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
@RequiredArgsConstructor
public class NotificationRecipientRepository implements NotificationRecipientRepositoryPort {
    private final NotificationRecipientMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, boolean isDeleted, LocalDateTime createdAt, int limit) {
        Criteria criteria = Criteria.where("recipientId").is(recipientId);

        if (isDeleted) {
            criteria = criteria.and("deletedAt").ne(null);
        } else {
            criteria = criteria.and("deletedAt").is(null);
        }

        MatchOperation matchRecipient = Aggregation.match(criteria);
        LookupOperation lookupNotification = LookupOperation.newLookup().from("notification").localField("notificationId").foreignField("_id").as("notification");
        UnwindOperation unwindNotification = Aggregation.unwind("notification");
        MatchOperation matchCreatedAt = Aggregation.match(where("notification.createdAt").lte(createdAt));

        SortOperation sortByCreatedAt = Aggregation.sort(Sort.Direction.ASC, "notification.createdAt");
        LimitOperation limitOperation = Aggregation.limit(limit + 1);

        Aggregation aggregation = Aggregation.newAggregation(matchRecipient, lookupNotification, unwindNotification, matchCreatedAt, sortByCreatedAt, limitOperation);
        AggregationResults<NotificationRecipientDocument> recipients = mongoTemplate.aggregate(aggregation, "notification_recipient", NotificationRecipientDocument.class);
        List<NotificationRecipient> recipientDomain = recipients.getMappedResults().stream().map(RecipientMapper::toDomain).toList();

        boolean hasNext = recipientDomain.size() > limit;

        if (hasNext) recipientDomain = recipientDomain.subList(0, limit);

        return new SliceOutput<>(recipientDomain, hasNext);
    }

    @Override
    public void save(NotificationRecipient notificationRecipient) {
        repository.save(RecipientMapper.toDocument(notificationRecipient));
    }

    @Override
    public boolean hasAnyUnread(UUID recipientId) {
        return repository.existsByRecipientIdAndReadIsFalse(recipientId);
    }

    @Override
    public int countUnread(UUID recipientId) {
        return repository.countByRecipientIdAndReadIsFalseAndDeletedAtIsNull(recipientId);
    }

    @Override
    public long markAllAsRead(UUID recipientId) {
        var result = mongoTemplate.updateMulti(query(where("recipientId").is(recipientId).and("isDeleted").is(false)), update("isRead", true), NotificationRecipientDocument.class);
        return result.getModifiedCount();
    }

    @Override
    public long markAsRead(UUID recipientId, UUID notificationId) {
        var result = mongoTemplate.updateFirst(query(where("recipientId").is(recipientId).and("notificationId").is(notificationId)), update("isRead", true), NotificationRecipientDocument.class);
        return result.getModifiedCount();
    }

    @Override
    public NotificationRecipient getByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId) {
        var recipientDocument = repository.findByRecipientIdAndNotificationId(recipientId, notificationId).orElseThrow(() -> new RecipientNotFoundException(recipientId, notificationId));
        return RecipientMapper.toDomain(recipientDocument);

    }

    @Override
    public void updateDeletedAt(NotificationRecipient recipient) {
        mongoTemplate.updateFirst(query(where("_id").is(recipient.getId())), update("deletedAt", recipient.getDeletionTime()), NotificationRecipientDocument.class);
    }

    @Override
    public List<NotificationRecipient> findAll() {
        return repository.findAll().stream().map(RecipientMapper::toDomain).toList();
    }
}
