package studydocs.notification.application.dto.command.notification;

import studydocs.notification.application.dto.base.Request;

import java.util.Map;
import java.util.UUID;

public record ReceiveNotificationCommand(UUID recipientId, UUID notificationId,
                                         Map<String, String> personalizedData) implements Request<Void> {

}
