package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record NotificationProjection(
        UUID id,
        UUID templateId,
        UUID senderId,
        String channel,
        String type,
        String snapshotSubject,
        String snapshotBody,
        LocalDateTime createdAt
) {
}