package studydocs.notificationservice.application.dto.input.recipient.create;

import studydocs.notificationservice.domain.model.entity.Notification;

import java.util.UUID;

public record ReceiveNotificationInput(Notification notification, UUID recipientId) {
}
