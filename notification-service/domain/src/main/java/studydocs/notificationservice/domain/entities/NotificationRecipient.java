package studydocs.notificationservice.domain.entities;

import studydocs.notificationservice.shared.exception.concrete.recipient.validation.MissingIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.validation.MissingNotificationIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.validation.MissingRecipientIdInRecipientException;

import java.util.Optional;
import java.util.UUID;

public class NotificationRecipient {
    private UUID id;
    private UUID recipientId;
    private UUID notificationId;
    private boolean isRead;
    private boolean isDeleted;
    private Notification notification;

    public NotificationRecipient(UUID id, UUID recipientId, UUID notificationId,
                                 boolean isRead, boolean isDeleted, Notification notification) {
        validationForLoad(id, recipientId, notificationId);
        this.id = id;
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
        this.notification = notification;
    }

    public NotificationRecipient(UUID recipientId, UUID notificationId) {
        validationForCreate(recipientId, notificationId);
        this.id = UUID.randomUUID();
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = false;
        this.isDeleted = false;
    }

    public UUID getId() {
        return id;
    }

    public Optional<Notification> getNotification() {
        return Optional.ofNullable(notification);
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    private void validationForLoad(UUID id, UUID recipientId, UUID notificationId) {
        if (id == null)
            throw new MissingIdInRecipientException();
        validationForCreate(recipientId, notificationId);
    }

    private void validationForCreate(UUID recipientId, UUID notificationId) {
        if (recipientId == null)
            throw new MissingRecipientIdInRecipientException();
        if (notificationId == null)
            throw new MissingNotificationIdInRecipientException();

    }
}