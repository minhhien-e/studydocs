package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
public record NotificationRecipientProjection(
        UUID id,
        UUID recipientId,
        String renderedSubject,
        String renderedBody,
        boolean isRead,
        LocalDateTime receivedAt,
        LocalDateTime deletedAt,
        NotificationProjection notification
) {
}
