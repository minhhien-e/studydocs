package studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.domain.model.entity.Template;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.TemplateDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.mapper.TemplateMapper;
import studydocs.notificationservice.infrastructure.outbound.persistence.repository.template.TemplateMongoRepository;
import studydocs.notificationservice.shared.exception.infrastructure.DatabaseUpdateFailureException;
import studydocs.notificationservice.shared.exception.infrastructure.ResourceNotFoundException;

import static studydocs.notificationservice.infrastructure.outbound.persistence.mapper.TemplateMapper.toDomain;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryAdapter implements TemplateRepositoryPort {
    private final TemplateMongoRepository notificationTemplateMongoRepository;
    private final MongoTemplate mongoTemplate;
    private final static String RESOURCE_TYPE = "mẫu thông báo";

    @Override
    public void save(Template notificationTemplate) {
        TemplateDocument document = TemplateMapper.toDocument(notificationTemplate);
        notificationTemplateMongoRepository.save(document);
    }

    @Override
    public Template getByName(TemplateName name) {
        TemplateDocument document = notificationTemplateMongoRepository.findByName(name.getValue()).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_TYPE));
        return toDomain(document);
    }

    @Override
    public void updateName(Template template) {
        Query query = new Query(Criteria.where("_id").is(template.getId()));
        Update update = new Update().set("name", template.getName().getValue());
        var result = mongoTemplate.updateFirst(query, update, TemplateDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("Thay đổi tên mẫu thông báo");
    }

    @Override
    public void updateSubject(Template template) {
        Query query = new Query(Criteria.where("_id").is(template.getId()));
        Update update = new Update().set("subjectTemplate", template.getSubjectTemplate().value());
        var result = mongoTemplate.updateFirst(query, update, TemplateDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("Thay đổi tiêu đề mẫu thông báo");
    }

    @Override
    public void updateBody(Template template) {
        Query query = new Query(Criteria.where("_id").is(template.getId()));
        Update update = new Update().set("bodyTemplate", template.getBodyTemplate().value());
        var result = mongoTemplate.updateFirst(query, update, TemplateDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("Thay đổi khung thông báo");
    }

    @Override
    public void updateDescription(Template template) {
        Query query = new Query(Criteria.where("_id").is(template.getId()));
        Update update = new Update().set("description", template.getDescription());
        var result = mongoTemplate.updateFirst(query, update, TemplateDocument.class).getModifiedCount();
        if (result == 0) throw new DatabaseUpdateFailureException("Thay đổi khung thông báo");
    }
}
