package studydocs.notification.application.dto.payload;

import java.util.UUID;

public record NotificationReceivePayload(UUID notificationId,
                                         UUID notificationRecipientId) {
}
