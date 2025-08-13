package studydocs.notificationservice.infrastructure.outbound.persistence.mapper;

import studydocs.notificationservice.domain.entity.NotificationTemplate;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationTemplateDocument;

public final class NotificationTemplateMapper {
    public static NotificationTemplateDocument toDocument(NotificationTemplate domain) {
        return NotificationTemplateDocument.builder()
                .id(domain.getId())
                .name(domain.getName().getValue())
                .channel(domain.getChannel().getChannel())
                .subjectTemplate(domain.getSubjectTemplate().value())
                .bodyTemplate(domain.getBodyTemplate().value())
                .description(domain.getDescription().orElse(null))
                .createdAt(domain.getCreatedAt().getValue())
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
