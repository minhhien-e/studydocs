package studydocs.notificationservice.application.dto.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record TemplateDto(
        UUID id,
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description,
        LocalDateTime creationTime,
        LocalDateTime updatedTime
) {
}