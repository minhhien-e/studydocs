package studydocs.notification.application.dto.view;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * View model for template API responses.
 * Represents template information for UI/client consumption.
 * Mapped from TemplateProjection in use cases.
 */
public record TemplateView(
        UUID id,
        String name,
        String channel,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
