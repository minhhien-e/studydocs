package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Builder
public record NotificationProjection(
        UUID id,
        UUID templateId,
        UUID senderId,
        Optional<String> senderName,
        String channel,
        String type,
        String snapshotSubject,
        String snapshotBody,
        LocalDateTime createdAt
) {
}