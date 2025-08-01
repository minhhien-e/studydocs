package studydocs.notificationservice.domain.entities;

import studydocs.notificationservice.shared.exception.concrete.recipient.MissingIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.MissingNotificationIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.MissingRecipientIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationAlreadyDeletedException;

import java.util.Optional;
import java.util.UUID;

public class NotificationRecipient {
    private final UUID id;
    private final UUID recipientId;
    private final UUID notificationId;
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

    public void read() {
        if (isDeleted)
            throw new NotificationAlreadyDeletedException();
        isRead = true;
    }
    public void delete() {
        isDeleted = true;
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