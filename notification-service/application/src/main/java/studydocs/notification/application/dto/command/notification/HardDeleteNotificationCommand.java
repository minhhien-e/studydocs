package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record HardDeleteNotificationCommand(UUID notificationId, UUID requesterId) implements Request<Void> {
}
