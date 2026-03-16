package studydocs.notification.api.dto.view;

import java.util.Map;

public record NotificationMetadataView(
        String groupName,
        Map<String, String> items
) {}
