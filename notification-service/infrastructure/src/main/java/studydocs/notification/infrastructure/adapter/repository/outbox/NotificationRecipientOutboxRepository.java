package studydocs.notification.infrastructure.adapter.repository.outbox;

import io.github.domain.port.DomainEventRegistry;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.repository.base.AbstractMongoNestedOutboxRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

@Repository
public class NotificationRecipientOutboxRepository extends AbstractMongoNestedOutboxRepository {
    public NotificationRecipientOutboxRepository(MongoTemplate mongoTemplate, DomainEventSerializer domainEventSerializer, DomainEventRegistry domainEventRegistry) {
        super(mongoTemplate, domainEventSerializer, domainEventRegistry);
    }

    @Override
    protected Class<?> getEntityClass() {
        return NotificationRecipientEntity.class;
    }
}
