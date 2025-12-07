package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.readmodel.TemplateReadModel;

import java.util.List;
import java.util.UUID;

public interface NotificationTemplateRepository {
    TemplateReadModel getById(UUID id);

    List<TemplateReadModel> findAll();

    List<TemplateReadModel> getByChannel(String channel);
}
