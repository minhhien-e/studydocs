package studydocs.notification.application.dto.command.notification;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record SendNotificationCommand(UUID notificationId, UUID notificationRecipientId) implements Request<Void> {
}
