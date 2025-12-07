package studydocs.notification.application.dto.command.notification;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record HardDeleteNotificationCommand(UUID notificationId, UUID requesterId) implements Request<Void> {
}
