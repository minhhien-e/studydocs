package studydocs.notificationservice.application.dto.input.recipient.delete;

import java.util.UUID;

public record HardDeleteNotificationInput(UUID notificationId, UUID requesterId) {
}
