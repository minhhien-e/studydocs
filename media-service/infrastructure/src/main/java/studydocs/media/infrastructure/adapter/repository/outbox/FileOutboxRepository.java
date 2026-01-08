package studydocs.media.infrastructure.adapter.repository.outbox;

import io.github.domain.port.DomainEventRegistry;
import io.github.domain.port.DomainEventSerializer;
import io.github.infrastructure.mongo.repository.base.AbstractMongoNestedOutboxRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.media.infrastructure.persistence.entity.FileEntity;
@Repository
public class FileOutboxRepository extends AbstractMongoNestedOutboxRepository {
    public FileOutboxRepository(MongoTemplate mongoTemplate, DomainEventSerializer domainEventSerializer, DomainEventRegistry domainEventRegistry) {
        super(mongoTemplate, domainEventSerializer, domainEventRegistry);
    }

    @Override
    protected Class<?> getEntityClass() {
        return FileEntity.class;
    }
}
