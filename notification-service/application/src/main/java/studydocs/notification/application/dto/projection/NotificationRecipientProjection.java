package studydocs.notification.application.dto.projection;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record NotificationRecipientProjection(
        UUID id,
        UUID recipientId,
        UUID notificationId,
        boolean isRead,
        Map<String,String> personalizedData,
        LocalDateTime receivedAt,
        LocalDateTime deletedAt
) {}
