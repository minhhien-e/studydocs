package studydocs.notification.application.dto.command.notification;



import studydocs.notification.application.dto.base.Request;

import java.util.Map;
import java.util.UUID;

public record AddNotificationCommand(
        UUID senderId,
        UUID templateId,
        String channel,
        String category,
        Map<String, String> templateData,
        Map<UUID, Map<String, String>> personalizedData) implements Request<Void> {
}
