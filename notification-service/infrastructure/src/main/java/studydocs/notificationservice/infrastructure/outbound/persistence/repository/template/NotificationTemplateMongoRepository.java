package studydocs.notificationservice.infrastructure.outbound.persistence.repository.template;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationTemplateDocument;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateMongoRepository extends MongoRepository<NotificationTemplateDocument, UUID> {
    Optional<NotificationTemplateDocument> findByName(String name);
    List<NotificationTemplateDocument> findAllByChannel(String channel);
    List<NotificationTemplateDocument> searchByNameLikeIgnoreCase(String name);
}
