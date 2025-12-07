package studydocs.notification.application.dto.command.notification;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record MarkAllAsReadCommand(
        UUID recipientId
) implements Request<Void> {
}