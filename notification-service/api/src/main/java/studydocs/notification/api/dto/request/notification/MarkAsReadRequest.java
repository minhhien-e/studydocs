package studydocs.notification.api.dto.request.notification;

import java.util.UUID;

public record MarkAsReadRequest(UUID notificationId) {
}
