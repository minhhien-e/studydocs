package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record SoftDeleteNotificationCommand(UUID notificationId, UUID requesterId) implements Request<Void> {
}
