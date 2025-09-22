package studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.domain.model.aggregate.UserNotificationAggregate;
import studydocs.notificationservice.domain.model.entity.Recipient;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.RecipientDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.RecipientMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.recipient.NotificationRecipientMongoRepository;
import studydocs.notificationservice.shared.exception.infrastructure.DatabaseUpdateFailureException;
import studydocs.notificationservice.shared.exception.infrastructure.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
@RequiredArgsConstructor
public class RecipientRepositoryAdapter implements RecipientRepositoryPort {
    private final NotificationRecipientMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }


    @Override
    public UserNotificationAggregate findByRecipientId(UUID recipientId) {
        var recipients = repository.findAllByRecipientIdAndRead(recipientId, false).stream().map(RecipientMapper::toDomain).toList();
        return new UserNotificationAggregate(recipientId, recipients);
    }

    @Override
    public void save(Recipient notificationRecipient) {
        repository.save(RecipientMapper.toDocument(notificationRecipient));
    }

    @Override
    public int countUnread(UUID recipientId) {
        return repository.countByRecipientIdAndReadIsFalseAndDeletedAtIsNull(recipientId);
    }

    @Override
    public void markAllAsRead(UUID recipientId) {
        var result = mongoTemplate.updateMulti(query(where("recipientId").is(recipientId).and("isDeleted").is(false)), update("isRead", true), RecipientDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("đánh dấu đọc");
    }

    @Override
    public void markAsRead(UUID recipientId, UUID notificationId) {
        var result = mongoTemplate.updateFirst(query(where("recipientId").is(recipientId).and("notificationId").is(notificationId)), update("isRead", true), RecipientDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("đánh dấu tất cả đã đọc");
    }

    @Override
    public UserNotificationAggregate getByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId) {
        var recipientDocument = repository.findByRecipientIdAndNotificationId(recipientId, notificationId).orElseThrow(() -> new ResourceNotFoundException("Thông báo"));
        return new UserNotificationAggregate(recipientId, List.of(RecipientMapper.toDomain(recipientDocument)));

    }

    @Override
    public void updateDeletedAt(Recipient recipient) {
        mongoTemplate.updateFirst(query(where("_id").is(recipient.getId())), update("deletedAt", recipient.getDeletionTime()), RecipientDocument.class);
    }

    @Override
    public List<Recipient> findAll() {
        return repository.findAll().stream().map(RecipientMapper::toDomain).toList();
    }


}
