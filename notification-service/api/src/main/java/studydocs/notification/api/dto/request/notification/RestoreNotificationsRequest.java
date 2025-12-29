package studydocs.notification.api.dto.request.notification;

import java.util.List;
import java.util.UUID;

public record RestoreNotificationsRequest(List<UUID> notificationIds) {
}
