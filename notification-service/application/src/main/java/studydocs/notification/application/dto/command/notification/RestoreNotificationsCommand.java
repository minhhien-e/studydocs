package studydocs.notification.application.dto.command.notification;



import studydocs.notification.application.dto.base.Request;

import java.util.List;
import java.util.UUID;

public record RestoreNotificationsCommand(List<UUID> notificationIds, UUID recipientId) implements Request<Void> {
}
