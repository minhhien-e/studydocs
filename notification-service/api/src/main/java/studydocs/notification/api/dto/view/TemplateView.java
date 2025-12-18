package studydocs.notification.api.dto.view;

import java.time.LocalDateTime;
import java.util.UUID;

public record TemplateView(
        UUID id,
        String name,
        String channel,
        String description,
        String templateSubject,
        String templateBody,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
