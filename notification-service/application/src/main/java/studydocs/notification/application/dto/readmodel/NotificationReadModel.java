package studydocs.notification.application.dto.readmodel;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationReadModel(
        UUID id,
        String senderName,
        String subject,
        String body,
        String type,
        boolean isRead,
        LocalDateTime deletedAt,
        LocalDateTime receivedAt
) {
}
