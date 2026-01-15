package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record ReceiveNotificationCommand(
        UUID notificationId,
        RecipientData recipientData
) implements Request<Void> {
}
