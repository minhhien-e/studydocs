package studydocs.notification.application.dto.view;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationRecipientView(
        UUID id,
        String senderName,
        String subject,
        String body,
        String type,
        boolean isRead,
        LocalDateTime receivedAt,
        LocalDateTime deletedAt
) {
}
