package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.AggregateRoot;
import studydocs.notification.domain.event.NotificationReceivedEvent;
import studydocs.notification.domain.exception.notification.CannotMarkDeletedNotificationAsReadException;
import studydocs.notification.domain.exception.notification.CannotMarkDeletedNotificationAsUnreadException;
import studydocs.notification.domain.exception.notification.NotificationAlreadySoftDeletedException;
import studydocs.notification.domain.exception.notification.NotificationNotSoftDeletedException;
import studydocs.notification.domain.vo.NotificationDeletionTime;
import studydocs.notification.domain.vo.NotificationReceptionTime;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class NotificationRecipient extends AggregateRoot {
    private UUID recipientId;
    private UUID notificationId;
    private boolean isRead;
    private String renderedSubject;
    private String renderedBody;
    private NotificationReceptionTime receptionTime;
    private NotificationDeletionTime deletedAt;

    /// Constructor
    private NotificationRecipient(UUID id, long version) {
        super(id, version);
    }

    private NotificationRecipient() {
        super();
    }

    /// Business logic
    public void markAsRead() {
        if (isDeleted()) {
            throw new CannotMarkDeletedNotificationAsReadException();
        }
        if (this.isRead) {
            return;
        }
        this.isRead = true;
    }

    public void markAsUnread() {
        if (isDeleted()) {
            throw new CannotMarkDeletedNotificationAsUnreadException();
        }
        if (!this.isRead) {
            return;
        }
        this.isRead = false;
    }

    public void softDelete() {
        if (isDeleted()) {
            throw new NotificationAlreadySoftDeletedException(notificationId);
        }
        this.deletedAt = new NotificationDeletionTime(LocalDateTime.now());
    }

    public void restore() {
        if (!isDeleted()) {
            throw new NotificationNotSoftDeletedException(notificationId);
        }
        this.deletedAt = null;
    }

    /// Helper method
    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    /// Factory method
    public static NotificationRecipient create(UUID notificationId, UUID recipientId, String renderedSubject, String renderedBody) {
        NotificationRecipient recipient = new NotificationRecipient();
        recipient.recipientId = recipientId;
        recipient.notificationId = notificationId;
        recipient.isRead = false;
        recipient.renderedSubject = renderedSubject;
        recipient.renderedBody = renderedBody;
        recipient.receptionTime = new NotificationReceptionTime(LocalDateTime.now());
        recipient.addDomainEvent(new NotificationReceivedEvent(notificationId, recipient.getId()));
        return recipient;
    }

    public static NotificationRecipient reconstruct(UUID id, long version, UUID notificationId, UUID recipientId, String renderedSubject, String renderedBody, boolean isRead, LocalDateTime receivedAt, LocalDateTime deletedAt) {
        NotificationRecipient recipient = new NotificationRecipient(id, version);
        recipient.recipientId = recipientId;
        recipient.notificationId = notificationId;
        recipient.isRead = isRead;
        recipient.renderedSubject = renderedSubject;
        recipient.renderedBody = renderedBody;
        recipient.receptionTime = new NotificationReceptionTime(receivedAt);
        if (deletedAt != null) {
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

    public String getRenderedSubject() {
        return renderedSubject;
    }

    public String getRenderedBody() {
        return renderedBody;
    }

    public NotificationReceptionTime getReceptionTime() {
        return receptionTime;
    }

}
