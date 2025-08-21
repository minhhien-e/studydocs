package studydocs.notificationservice.domain.entity;

import studydocs.notificationservice.shared.exception.concrete.recipient.MissingIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.MissingNotificationIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.MissingRecipientIdInRecipientException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationAlreadyDeletedException;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationRecipient {
    private final UUID id;
    private final UUID recipientId;
    private final UUID notificationId;
    private boolean isRead;
    private LocalDateTime deletedAt;
    private Notification notification;

    public NotificationRecipient(UUID id, UUID recipientId, UUID notificationId,
                                 boolean isRead, LocalDateTime deletedAt, Notification notification) {
        validationForLoad(id, recipientId, notificationId);
        this.id = id;
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = isRead;
        this.deletedAt = deletedAt;
        this.notification = notification;
    }

    public NotificationRecipient(UUID recipientId, UUID notificationId) {
        validationForCreate(recipientId, notificationId);
        this.id = UUID.randomUUID();
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = false;
    }

    public UUID getId() {
        return id;
    }

    public Notification getNotification() {
        return notification;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void read() {
        if (deletedAt != null)
            throw new NotificationAlreadyDeletedException();
        isRead = true;
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
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