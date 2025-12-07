package studydocs.notification.domain.entity;

import io.github.domain.aggregate.base.AggregateChild;
import studydocs.notification.domain.exception.notification.NotificationAlreadySoftDeletedException;
import studydocs.notification.domain.exception.notification.NotificationNotSoftDeletedException;
import studydocs.notification.domain.vo.NotificationDeletionTime;
import studydocs.notification.domain.vo.NotificationPersonalizedData;
import studydocs.notification.domain.vo.NotificationReceptionTime;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class NotificationRecipient extends AggregateChild {
    private UUID recipientId;
    private UUID notificationId;
    private boolean isRead;
    private NotificationPersonalizedData personalizedData;
    private NotificationReceptionTime receptionTime;
    private NotificationDeletionTime deletedAt;

    /// Constructor
    public NotificationRecipient(UUID id) {
        super(id);
    }

    public NotificationRecipient() {
    }

    /// Business logic
    public void markAsRead() {
        this.isRead = true;
    }

    public void updatePersonalizedData(NotificationPersonalizedData personalizedData) {
        this.personalizedData = personalizedData;
    }

    public void softDelete(NotificationDeletionTime deletedAt) {
        if (this.deletedAt != null) {
            throw new NotificationAlreadySoftDeletedException(notificationId);
        }
        this.deletedAt = deletedAt;
    }

    public void restore() {
        if (deletedAt == null) {
            throw new NotificationNotSoftDeletedException(notificationId);
        }
        deletedAt = null;
    }

    /// Factory method
    public static NotificationRecipient create(UUID recipientId, UUID notificationId, Map<String, String> personalizedData) {
        NotificationRecipient recipient = new NotificationRecipient();
        recipient.recipientId = recipientId;
        recipient.notificationId = notificationId;
        recipient.markAsRead();
        recipient.updatePersonalizedData(new NotificationPersonalizedData(personalizedData));
        recipient.receptionTime = new NotificationReceptionTime(LocalDateTime.now());
        return recipient;
    }

    public static NotificationRecipient reconstruct(UUID id, UUID recipientId, UUID notificationId, Map<String, String> personalizedData, boolean isRead,LocalDateTime receivedAt ,LocalDateTime deletedAt) {
        NotificationRecipient recipient = new NotificationRecipient(id);
        recipient.recipientId = recipientId;
        recipient.notificationId = notificationId;
        recipient.isRead = isRead;
        recipient.personalizedData = new NotificationPersonalizedData(personalizedData);
        recipient.receptionTime = new NotificationReceptionTime(receivedAt);
        if(deletedAt != null) {
            recipient.deletedAt = new NotificationDeletionTime(deletedAt);
        }
        return recipient;
    }

    /// Getter
    public UUID getRecipientId() {
        return recipientId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public boolean isRead() {
        return isRead;
    }

    public Optional<NotificationDeletionTime> getDeletedAt() {
        return Optional.ofNullable(deletedAt);
    }

    public NotificationPersonalizedData getPersonalizedData() {
        return personalizedData;
    }

    public NotificationReceptionTime getReceptionTime() {
        return receptionTime;
    }
}
