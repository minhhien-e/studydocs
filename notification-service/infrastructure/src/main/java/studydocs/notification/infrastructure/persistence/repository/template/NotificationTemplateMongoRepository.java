package studydocs.notification.infrastructure.persistence.repository.template;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;

import java.util.List;
import java.util.UUID;

public interface NotificationTemplateMongoRepository extends MongoRepository<NotificationTemplateEntity, UUID> {
    boolean existsByName(String name);

    List<NotificationTemplateEntity> findAllByChannel(String channel);
}
