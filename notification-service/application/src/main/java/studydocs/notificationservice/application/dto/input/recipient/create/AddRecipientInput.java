package studydocs.notificationservice.application.dto.input.recipient.create;

import studydocs.notificationservice.domain.model.entity.NotificationRecipient;

import java.util.UUID;

public record AddRecipientInput(UUID recipientId, UUID notificationId) {
    public NotificationRecipient toDomain() {
        return new NotificationRecipient(recipientId, notificationId);
    }
}
