package studydocs.notification.application.dto.readmodel;

import java.time.LocalDateTime;
import java.util.UUID;

public record TemplateReadModel(
        UUID id,
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
