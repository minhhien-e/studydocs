package studydocs.notificationservice.application.dto.input.recipient.update;

import java.util.List;
import java.util.UUID;

public record RestoreNotificationsInput(List<UUID> notificationIds, UUID recipientId) {
}
