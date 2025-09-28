package studydocs.notificationservice.application.dto.input.recipient.read;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetNotificationByRecipientIdInput(UUID recipientId,
                                                boolean isDeleted,
                                                LocalDateTime createdAt) {
}
