package studydocs.notification.application.dto.payload;

import java.util.UUID;

public record NotificationRecipientReadyPayload(UUID notificationId,
                                                UUID notificationRecipientId) {
}
