package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record MarkAsReadCommand(UUID notificationId, UUID recipientId) implements Request<Void> {
}
