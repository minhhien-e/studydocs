package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.domain.aggregate.NotificationTemplate;
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
                entity.getType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static NotificationTemplate toDomain(NotificationTemplateEntity entity) {
        return NotificationTemplate.reconstruct(entity.getId(), entity.getVersion(), entity.getName(), entity.getChannel(), entity.getSubjectTemplate(), entity.getBodyTemplate(), entity.getDescription(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getType());
    }

    public static void updateEntity(NotificationTemplateEntity entity, NotificationTemplate domain) {
        if (entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setBodyTemplate(domain.getBodyTemplate().value());
        entity.setChannel(domain.getChannel().value());
        entity.setName(domain.getName().value());
        entity.setSubjectTemplate(domain.getSubjectTemplate().value());
        entity.setDescription(domain.getDescription());
        entity.setType(domain.getType().value());
        entity.setCreatedAt(domain.getCreatedAt().value());
        entity.setUpdatedAt(domain.getUpdatedAt().value());
    }
}
