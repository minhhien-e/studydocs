package studydocs.notification.infrastructure.persistence.repository.template.impl;

import io.github.infrastructure.mongo.repository.base.AbstractEntityMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import studydocs.notification.domain.entity.NotificationTemplate;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.infrastructure.mapper.NotificationTemplateMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import studydocs.notification.infrastructure.persistence.repository.template.NotificationTemplateMongoRepository;

import java.util.UUID;

@Repository
public class NotificationTemplateMongoRepositoryAdapter extends AbstractEntityMongoRepository<NotificationTemplate, NotificationTemplateEntity> implements NotificationTemplateRepository {
    private final NotificationTemplateMongoRepository mongoRepository;
    public NotificationTemplateMongoRepositoryAdapter(MongoTemplate mongoTemplate, NotificationTemplateMongoRepository mongoRepository) {
        super(mongoTemplate);
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
        return mongoRepository.findById(id).map(NotificationTemplateMapper::toDomain).orElseThrow(()-> new NotificationTemplateNotFoundException(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return mongoRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return mongoRepository.existsByName(name);
    }
}
