package studydocs.notification.application.dto.projection;

import java.util.Map;

public record NotificationMetadataProjection(
        String groupName,
        Map<String, String> items
) {
}
