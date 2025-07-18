package studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetNotificationByRecipientIdInputModel(UUID recipientId,
                                                     LocalDateTime createdAt) {
}
