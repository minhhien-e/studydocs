package studydocs.notificationservice.infrastructure.inbound.web.request.recipient.read;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.shared.paging.SliceInput;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record GetNotificationByRecipientIdRequest(
        UUID recipientId,
        Optional<LocalDateTime> createdAt,
        int limit) {
    public SliceInput<GetNotificationByRecipientIdInput> toInput() {
        var limit = this.limit <= 0 ? 10 : this.limit;
        var request = new GetNotificationByRecipientIdInput(recipientId,
                createdAt.orElse(LocalDateTime.now()));
        return new SliceInput<>(request, limit);
    }
}
