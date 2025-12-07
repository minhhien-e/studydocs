package studydocs.notification.application.dto.command.notification;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record MarkAsReadCommand(UUID notificationId, UUID recipientId) implements Request<Void> {
}
