package studydocs.notification.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notification.infrastructure.adapter.web.DomainToHttpExceptionMapper;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateMongoRepository extends MongoRepository<NotificationTemplateEntity, UUID> {
    boolean existsByName(String name);

    List<NotificationTemplateEntity> findAllByChannel(String channel);

    Optional<NotificationTemplateEntity> findByName(String name);
}
