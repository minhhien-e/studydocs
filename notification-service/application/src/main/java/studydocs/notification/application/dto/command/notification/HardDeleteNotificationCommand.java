package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.List;
import java.util.UUID;

@Builder
public record HardDeleteNotificationCommand(List<UUID> notificationIds, UUID requesterId) implements Request<Void> {
}
