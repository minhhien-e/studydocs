package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;

import java.util.List;
import java.util.UUID;

public interface NotificationTemplateQueries {
    TemplateProjection getById(UUID id);

    List<TemplateProjection> findAll();

    List<TemplateProjection> getByChannel(String channel);
}
