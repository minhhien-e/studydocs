package studydocs.notification.api.dto.request.notification;

import java.util.Map;
import java.util.UUID;

public record AddNotificationRequest(
        UUID templateId,
        String channel,
        String category,
        Map<String, String> templateData,
        Map<UUID, Map<String, String>> personalizedData) {
}
