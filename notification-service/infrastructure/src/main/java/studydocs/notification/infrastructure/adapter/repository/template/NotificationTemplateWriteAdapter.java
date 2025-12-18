package studydocs.notification.infrastructure.adapter.repository.template;

import io.github.domain.aggregate.base.AggregateChild;
import io.github.domain.entity.base.DomainEntity;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.entity.base.MongoEntity;
import io.github.infrastructure.mongo.helper.MongoEntityWriter;
import io.github.infrastructure.mongo.repository.base.AbstractAggregateMongoRepository;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.aggregate.NotificationTemplate;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.infrastructure.mapper.NotificationTemplateMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import studydocs.notification.infrastructure.persistence.repository.NotificationTemplateMongoRepository;

import java.util.UUID;

@Repository
public class NotificationTemplateWriteAdapter extends AbstractAggregateMongoRepository<NotificationTemplate, NotificationTemplateEntity> implements NotificationTemplateRepository {
    private final NotificationTemplateMongoRepository mongoRepository;

    public NotificationTemplateWriteAdapter(NotificationTemplateMongoRepository mongoRepository,
                                            MongoEntityWriter mongoEntityWriter,
                                            DomainEventSerializer domainEventSerializer
    ) {
        super(mongoEntityWriter, domainEventSerializer);
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Class<?> getEntityClass() {
        return NotificationTemplateEntity.class;
    }

    @Override
    public NotificationTemplateEntity toEntity(NotificationTemplate domainEntity) {
        return NotificationTemplateMapper.toEntity(domainEntity);
    }

    @Override
    public NotificationTemplate getById(UUID id) {
        return mongoRepository.findById(id).map(NotificationTemplateMapper::toDomain).orElseThrow(() -> new NotificationTemplateNotFoundException(id));
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
    protected Class<?> getChildEntityClass(AggregateChild aggregateChild) {
        return null;
    }

    @Override
    protected MongoEntity toChildEntity(DomainEntity domainEntity) {
        return null;
    }
}
