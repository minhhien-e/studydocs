package studydocs.notification.infrastructure.persistence.repository.template.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.out.repository.NotificationTemplateRepository;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.infrastructure.mapper.NotificationTemplateMapper;
import studydocs.notification.infrastructure.persistence.repository.template.NotificationTemplateMongoRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationTemplateMongoReadAdapter implements NotificationTemplateRepository {
    private final NotificationTemplateMongoRepository templateMongoRepository;

    @Override
    public TemplateReadModel getById(UUID id) {
        return templateMongoRepository.findById(id).map(NotificationTemplateMapper::toReadModel).orElseThrow(() -> new NotificationTemplateNotFoundException(id));
    }

    @Override
    public List<TemplateReadModel> findAll() {
        return templateMongoRepository.findAll().stream().map(NotificationTemplateMapper::toReadModel).toList();
    }

    @Override
    public List<TemplateReadModel> getByChannel(String channel) {
        return templateMongoRepository.findAllByChannel(channel).stream().map(NotificationTemplateMapper::toReadModel).toList();
    }
}
