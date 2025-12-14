package studydocs.notification.infrastructure.adapter.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.port.out.repository.NotificationTemplateQueries;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.infrastructure.mapper.NotificationTemplateMapper;
import studydocs.notification.infrastructure.persistence.repository.NotificationTemplateMongoRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationTemplateQueryAdapter implements NotificationTemplateQueries {
    private final NotificationTemplateMongoRepository templateMongoRepository;

    @Override
    public TemplateProjection getById(UUID id) {
        return templateMongoRepository.findById(id).map(NotificationTemplateMapper::toProjection).orElseThrow(() -> new NotificationTemplateNotFoundException(id));
    }

    @Override
    public List<TemplateProjection> findAll() {
        return templateMongoRepository.findAll().stream().map(NotificationTemplateMapper::toProjection).toList();
    }

    @Override
    public List<TemplateProjection> getByChannel(String channel) {
        return templateMongoRepository.findAllByChannel(channel).stream().map(NotificationTemplateMapper::toProjection).toList();
    }
}
