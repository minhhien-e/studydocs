package studydocs.notificationservice.application.dto.input.notification.read;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetNotificationByRecipientIdInput(UUID recipientId,
                                                LocalDateTime createdAt) {
}
