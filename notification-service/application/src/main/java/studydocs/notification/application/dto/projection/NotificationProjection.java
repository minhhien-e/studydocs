package studydocs.notification.application.dto.projection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record NotificationProjection(
        UUID id,
        UUID templateId,
        UUID senderId,
        String channel,
        String category,
        Map<String, String> templateData,
        LocalDateTime createdAt,
        List<NotificationRecipientProjection> notificationRecipients,
        TemplateProjection template
) {
}