package studydocs.notification.infrastructure.adapter.repository.notification;

import io.github.infrastructure.mongo.repository.base.AbstractEntityMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationMongoRepository;

import java.util.UUID;

@Repository
public class NotificationWriteAdapter
        extends AbstractEntityMongoRepository<Notification, NotificationEntity>
        implements NotificationRepository {
    private final NotificationMongoRepository mongoDataRepository;

    public NotificationWriteAdapter(MongoTemplate mongoTemplate, NotificationMongoRepository mongoDataRepository) {
        super(mongoTemplate);
        this.mongoDataRepository = mongoDataRepository;
    }

    @Override
    public Notification getById(UUID id) {
        return mongoDataRepository.findById(id).map(NotificationMapper::toDomain).orElseThrow(()->new NotificationNotFoundException(id));
    }

    @Override
    public Class<?> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public NotificationEntity toEntity(Notification aggregate) {
        return NotificationMapper.toEntity(aggregate);
    }

}
