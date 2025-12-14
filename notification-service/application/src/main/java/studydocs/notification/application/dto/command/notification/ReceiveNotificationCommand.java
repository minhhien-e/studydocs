package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.Map;
import java.util.UUID;

@Builder
public record ReceiveNotificationCommand(
        UUID notificationId,
        UUID recipientId,
        Map<String, String> subjectData,
        Map<String, String> bodyData
) implements Request<Void> {
}
