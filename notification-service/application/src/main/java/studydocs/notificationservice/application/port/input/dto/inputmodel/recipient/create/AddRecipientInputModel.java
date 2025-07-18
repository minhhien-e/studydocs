package studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create;

import studydocs.notificationservice.domain.entities.NotificationRecipient;

import java.util.UUID;

public record AddRecipientInputModel(UUID recipientId, UUID notificationId) {
    public NotificationRecipient toDomain() {
        return new NotificationRecipient(recipientId, notificationId);
    }
}
