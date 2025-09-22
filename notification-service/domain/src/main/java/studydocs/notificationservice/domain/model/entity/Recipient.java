package studydocs.notificationservice.domain.model.entity;

import studydocs.notificationservice.domain.exceptions.entity.notification.NotificationAlreadyDeletedException;
import studydocs.notificationservice.domain.exceptions.entity.notification.NotificationNotDeletedException;
import studydocs.notificationservice.domain.model.valueobject.date.past.NotificationDeletionTime;

import java.time.LocalDateTime;
import java.util.UUID;

public class Recipient {
    private final UUID id;
    private final UUID recipientId;
    private final UUID notificationId;
    private boolean isRead;
    private NotificationDeletionTime deletionTime;

    public Recipient(UUID id, UUID recipientId, UUID notificationId,
                     boolean isRead, LocalDateTime deletedAt) {
        this.id = id;
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = isRead;
        this.deletionTime = new NotificationDeletionTime(deletedAt);
    }

    public Recipient(UUID recipientId, UUID notificationId) {
        this.id = UUID.randomUUID();
        this.recipientId = recipientId;
        this.notificationId = notificationId;
        this.isRead = false;
    }

    public UUID getId() {
        return id;
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
        if (deletionTime != null) {
            throw new NotificationAlreadyDeletedException();
        }
        deletionTime = new NotificationDeletionTime(LocalDateTime.now());
    }


    public void restore() {
        if (deletionTime == null)
            throw new NotificationNotDeletedException();
        deletionTime = null;
    }
}