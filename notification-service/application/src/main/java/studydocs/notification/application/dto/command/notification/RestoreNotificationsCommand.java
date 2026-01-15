package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.List;
import java.util.UUID;

@Builder
public record RestoreNotificationsCommand(List<UUID> notificationIds, UUID recipientId) implements Request<Void> {
}
