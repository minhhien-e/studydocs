package studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete;

import java.util.UUID;

public record SoftDeleteNotificationRequest(UUID notificationId) {
}
