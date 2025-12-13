package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.Map;
import java.util.UUID;

@Builder
public record ReceiveNotificationCommand(
        UUID notificationId,
        UUID recipientId,
        Map<String, String> subjectData,  // Data for rendering subject
        Map<String, String> bodyData      // Data for rendering body
) implements Request<Void> {
}
