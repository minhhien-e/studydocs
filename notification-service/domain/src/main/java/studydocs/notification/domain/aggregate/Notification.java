package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.base.AggregateRoot;
import studydocs.notification.domain.vo.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification extends AggregateRoot {
    private UUID templateId;
    private UUID senderId;
    private NotificationCategory category;
    private NotificationChannel channel;
    private NotificationSnapshotSubject snapshotSubject;
    private NotificationSnapshotBody snapshotBody;
    private NotificationCreationTime createdAt;

    /// Constructor
    private Notification(UUID id) {
        super(id);
    }

    private Notification() {
        super();
    }

    /// Factory method
    public static Notification create(
            UUID senderId,
            UUID templateId,
            String channel,
            String category,
            String snapshotSubject,
            String snapshotBody
    ) {
        Notification notification = new Notification();
        notification.senderId = senderId;
        notification.templateId = templateId;
        notification.channel = new NotificationChannel(channel);
        notification.category = new NotificationCategory(category);
        notification.snapshotSubject = new NotificationSnapshotSubject(snapshotSubject);
        notification.snapshotBody = new NotificationSnapshotBody(snapshotBody);
        notification.createdAt = new NotificationCreationTime(LocalDateTime.now());

        return notification;
    }

    public static Notification reconstruct(UUID id,
                                           UUID templateId,
                                           UUID senderId,
                                           String category,
                                           String channel,
                                           String snapshotSubject,
                                           String snapshotBody,
                                           LocalDateTime createdAt) {
        Notification notification = new Notification(id);
        notification.senderId = senderId;
        notification.templateId = templateId;
        notification.category = new NotificationCategory(category);
        notification.channel = new NotificationChannel(channel);
        notification.snapshotSubject = new NotificationSnapshotSubject(snapshotSubject);
        notification.snapshotBody = new NotificationSnapshotBody(snapshotBody);
        notification.createdAt = new NotificationCreationTime(createdAt);
        return notification;
    }

    /// Getter
    public UUID getTemplateId() {
        return templateId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public NotificationCreationTime getCreatedAt() {
        return createdAt;
    }

    public NotificationSnapshotSubject getSnapshotSubject() {
        return snapshotSubject;
    }

    public NotificationSnapshotBody getSnapshotBody() {
        return snapshotBody;
    }

}
