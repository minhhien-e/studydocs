package studydocs.notification.api.dto.request.notification;

import java.util.List;
import java.util.UUID;

public record SoftDeleteNotificationRequest(List<UUID> notificationIds) {
}
