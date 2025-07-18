package studydocs.notificationservice.infrastructure.mongo.mapper;

import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationTemplateDocument;

public final class NotificationTemplateMapper {
    public static NotificationTemplateDocument toDocument(NotificationTemplate domain) {
        return NotificationTemplateDocument.builder()
                .name(domain.getName())
                .channel(domain.getChannel().name())
                .subjectTemplate(domain.getSubjectTemplate())
                .bodyTemplate(domain.getBodyTemplate())
                .description(domain.getDescription().orElse(null))
                .build();
    }

    public static NotificationTemplate toDomain(NotificationTemplateDocument document) {
        return new NotificationTemplate(document.getId(),
                document.getName(),
                document.getChannel(),
                document.getSubjectTemplate(),
                document.getBodyTemplate(),
                document.getDescription(),
                document.getCreatedAt(),
                document.getUpdatedAt());
    }
}
