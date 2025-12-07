package studydocs.notification.application.dto.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public record TemplateProjection(
        UUID id,
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedTime
) {}
