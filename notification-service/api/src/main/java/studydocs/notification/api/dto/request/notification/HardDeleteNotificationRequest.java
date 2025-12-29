package studydocs.notification.api.dto.request.notification;

import java.util.List;
import java.util.UUID;

public record HardDeleteNotificationRequest(List<UUID> notificationIds) {
}
