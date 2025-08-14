package studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.shared.paging.SliceInput;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record GetNotificationByRecipientIdRequest(
        UUID recipientId,
        Optional<LocalDateTime> createdAt,
        int limit) {
}
