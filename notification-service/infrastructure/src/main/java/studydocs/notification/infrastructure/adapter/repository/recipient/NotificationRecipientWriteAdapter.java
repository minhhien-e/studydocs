package studydocs.notification.infrastructure.adapter.repository.recipient;

import io.github.infrastructure.mongo.repository.base.AbstractEntityMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.infrastructure.mapper.NotificationRecipientMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationRecipientMongoRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificationRecipientWriteAdapter
        extends AbstractEntityMongoRepository<NotificationRecipient, NotificationRecipientEntity>
        implements NotificationRecipientRepository {

    private final NotificationRecipientMongoRepository mongoRepository;

    public NotificationRecipientWriteAdapter(
            MongoTemplate mongoTemplate,
            NotificationRecipientMongoRepository mongoRepository
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
}
