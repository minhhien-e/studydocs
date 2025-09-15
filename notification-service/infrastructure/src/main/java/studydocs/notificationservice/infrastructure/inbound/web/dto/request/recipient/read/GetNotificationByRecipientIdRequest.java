package studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record GetNotificationByRecipientIdRequest(
        UUID recipientId,
        boolean isDeleted,
        Optional<LocalDateTime> createdAt,
        int limit) {
}
