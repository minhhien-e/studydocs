package studydocs.notificationservice.adapter.output.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationTemplateDocument;
import studydocs.notificationservice.infrastructure.mongo.mapper.NotificationTemplateMapper;
import studydocs.notificationservice.infrastructure.mongo.repository.NotificationTemplateMongoRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationTemplateRepository implements NotificationTemplateRepositoryPort {
    private final NotificationTemplateMongoRepository notificationTemplateMongoRepository;

    @Override
    public void save(NotificationTemplate notificationTemplate) {
        NotificationTemplateDocument document = NotificationTemplateMapper.toDocument(notificationTemplate);
        notificationTemplateMongoRepository.save(document);
    }

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
}
