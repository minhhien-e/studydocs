package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.domain.entity.NotificationTemplate;
import studydocs.notification.infrastructure.persistence.entity.NotificationTemplateEntity;

public final class NotificationTemplateMapper {
    public static TemplateProjection toProjection(NotificationTemplateEntity entity) {
        return new TemplateProjection(
                entity.getId(),
                entity.getName(),
                entity.getChannel(),
                entity.getSubjectTemplate(),
                entity.getBodyTemplate(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static TemplateReadModel toReadModel(NotificationTemplateEntity entity) {
        return new TemplateReadModel(entity.getId(),
                entity.getName(),
                entity.getChannel(),
                entity.getSubjectTemplate(),
                entity.getBodyTemplate(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static NotificationTemplateEntity toEntity(NotificationTemplate domainEntity) {
        return NotificationTemplateEntity.builder()
                .id(domainEntity.getId())
                .bodyTemplate(domainEntity.getBodyTemplate().value())
                .channel(domainEntity.getChannel().value())
                .name(domainEntity.getName().value())
                .subjectTemplate(domainEntity.getSubjectTemplate().value())
                .description(domainEntity.getDescription())
                .createdAt(domainEntity.getCreatedAt().value())
                .updatedAt(domainEntity.getUpdatedAt().value())
                .build();
    }

    public static NotificationTemplate toDomain(NotificationTemplateEntity entity) {
        return NotificationTemplate.reconstruct(entity.getId(), entity.getName(), entity.getChannel(), entity.getSubjectTemplate(), entity.getBodyTemplate(), entity.getDescription(), entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
