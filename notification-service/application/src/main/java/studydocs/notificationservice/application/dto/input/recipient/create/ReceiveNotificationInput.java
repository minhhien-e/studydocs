package studydocs.notificationservice.application.dto.input.recipient.create;

import java.util.UUID;

public record ReceiveNotificationInput(UUID notificationId, UUID recipientId) {
}
