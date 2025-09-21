package studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.domain.model.entity.NotificationTemplate;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationTemplateDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.NotificationTemplateMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.NotificationTemplateMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationTemplateRepository implements NotificationTemplateRepositoryPort {
    private final NotificationTemplateMongoRepository notificationTemplateMongoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void save(NotificationTemplate notificationTemplate) {
        NotificationTemplateDocument document = NotificationTemplateMapper.toDocument(notificationTemplate);
        notificationTemplateMongoRepository.save(document);
    }

    //region Find
    @Override
    public Optional<NotificationTemplate> findByName(String name) {
        Optional<NotificationTemplateDocument> document = notificationTemplateMongoRepository.findByName(name);
        return document.map(NotificationTemplateMapper::toDomain);
    }

    @Override
    public Optional<NotificationTemplate> findById(UUID id) {
        Optional<NotificationTemplateDocument> document = notificationTemplateMongoRepository.findById(id);
        return document.map(NotificationTemplateMapper::toDomain);
    }

    //endregion
    //region Find All
    @Override
    public List<NotificationTemplate> findAll() {
        return notificationTemplateMongoRepository.findAll().stream()
                .map(NotificationTemplateMapper::toDomain)
                .toList();
    }

    @Override
    public List<NotificationTemplate> searchByName(String name) {
        return notificationTemplateMongoRepository.searchByNameLikeIgnoreCase(name).stream()
                .map(NotificationTemplateMapper::toDomain)
                .toList();
    }

    @Override
    public List<NotificationTemplate> findByChannel(String channel) {
        return notificationTemplateMongoRepository.findAllByChannel(channel).stream()
                .map(NotificationTemplateMapper::toDomain)
                .toList();
    }

    //endregion
    //region Update
    @Override
    public long updateName(UUID id, String newName) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("name", newName);
        var result = mongoTemplate.updateFirst(query, update, NotificationTemplateDocument.class);
        return result.getModifiedCount();
    }

    @Override
    public long updateSubject(UUID id, String newSubject) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("subjectTemplate", newSubject);
        var result = mongoTemplate.updateFirst(query, update, NotificationTemplateDocument.class);
        return result.getModifiedCount();
    }

    @Override
    public long updateBody(UUID id, String newBody) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("bodyTemplate", newBody);
        var result = mongoTemplate.updateFirst(query, update, NotificationTemplateDocument.class);
        return result.getModifiedCount();
    }

    @Override
    public long updateDescription(UUID id, String newDescription) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("description", newDescription);
        var result = mongoTemplate.updateFirst(query, update, NotificationTemplateDocument.class);
        return result.getModifiedCount();
    }
    //endregion
}
