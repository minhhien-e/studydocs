package studydocs.notificationservice.infrastructure.outbound.persistence.repository.template;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.TemplateDocument;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TemplateMongoRepository extends MongoRepository<TemplateDocument, UUID> {
    Optional<TemplateDocument> findByName(String name);

    List<TemplateDocument> findAllByChannel(String channel);

    List<TemplateDocument> searchByNameLikeIgnoreCase(String name);
}
