package studydocs.notificationservice.domain.model.entity;

import studydocs.notificationservice.domain.exceptions.entity.notification.NotificationAlreadyDeletedException;
import studydocs.notificationservice.domain.model.valueobject.date.past.NotificationDeletionTime;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationRecipient {
    private final UUID id;
    private final UUID recipientId;
    private final UUID notificationId;
    private boolean isRead;
    private NotificationDeletionTime deletionTime;
    private Notification notification;

    public NotificationRecipient(UUID id, UUID recipientId, UUID notificationId,
                                 boolean isRead, LocalDateTime deletedAt, Notification notification) {
        this.id = id;
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = isRead;
        this.deletionTime = new NotificationDeletionTime(deletedAt);
        this.notification = notification;
    }

    public NotificationRecipient(UUID recipientId, UUID notificationId) {
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

    public NotificationDeletionTime getDeletionTime() {
        return deletionTime;
    }

    public void read() {
        if (deletionTime != null)
            throw new NotificationAlreadyDeletedException();
        isRead = true;
    }

    public void delete() {
        deletionTime = new NotificationDeletionTime(LocalDateTime.now());
    }


}