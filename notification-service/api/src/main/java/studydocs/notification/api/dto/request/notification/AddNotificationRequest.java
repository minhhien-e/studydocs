package studydocs.notification.api.dto.request.notification;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record AddNotificationRequest(
        UUID templateId,
        String channel,
        String type,
        Map<String, String> snapshotSubjectData,
        Map<String, String> snapshotBodyData,
        List<RecipientDataRequest> recipients
) {
}
