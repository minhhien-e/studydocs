package studydocs.notificationservice.application.dto.input.notification.delete;

import java.util.UUID;

public record HardDeleteNotificationInput(UUID notificationId, UUID requesterId) {
}
