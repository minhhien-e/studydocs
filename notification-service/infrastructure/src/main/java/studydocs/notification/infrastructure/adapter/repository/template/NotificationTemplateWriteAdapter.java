package studydocs.notification.infrastructure.adapter.repository.template;

import io.github.domain.aggregate.AggregateChild;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.exception.ResourceNotFoundException;
import io.github.infrastructure.mongo.helper.MongoHelper;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoEntityRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationTemplate;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.infrastructure.mapper.NotificationTemplateMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationTemplateMongoRepository;

import java.util.UUID;

@Repository
public class NotificationTemplateWriteAdapter extends AbstractAggregateMongoEntityRepository<NotificationTemplate, NotificationTemplateEntity> implements NotificationTemplateRepository {
    private final NotificationTemplateMongoRepository mongoRepository;

    public NotificationTemplateWriteAdapter(NotificationTemplateMongoRepository mongoRepository,
                                            DomainEventSerializer domainEventSerializer,
                                            MongoTemplate mongoTemplate,
                                            MongoHelper mongoHelper
    ) {
        super(domainEventSerializer, mongoTemplate, mongoHelper);
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Class<NotificationTemplateEntity> getEntityClass() {
        return NotificationTemplateEntity.class;
    }

    @Override
    public NotificationTemplate toDomainEntity(NotificationTemplateEntity entity) {
        return NotificationTemplateMapper.toDomain(entity);
    }

    @Override
    public void updateEntity(NotificationTemplateEntity snapshot, NotificationTemplate domainEntity) {
        NotificationTemplateMapper.updateEntity(snapshot, domainEntity);
    }

    @Override
    public NotificationTemplate getById(UUID id) {
        try {
            return super.getById(id);
        } catch (ResourceNotFoundException e) {
            throw new NotificationTemplateNotFoundException(id);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return mongoRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return mongoRepository.existsByName(name);
    }

    @Override
    protected AggregateChild getChildInstance(Class<? extends AggregateChild> aggregateChild) {
        return null;
    }

    @Override
    protected void updateChildEntity(Class<? extends AggregateChild> aggregateChildClass, AggregateChild child, MongoEntity childEntity) {
    }
}
