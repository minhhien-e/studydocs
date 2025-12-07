package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.base.AggregateRoot;
import io.github.domain.enums.DomainStatus;
import studydocs.notification.domain.entity.NotificationRecipient;
import studydocs.notification.domain.exception.recipient.RecipientNotFoundException;
import studydocs.notification.domain.vo.*;
import studydocs.notification.domain.event.NotificationReceivedEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Notification extends AggregateRoot {
    private UUID templateId;
    private UUID senderId;
    private NotificationCategory category;
    private NotificationChannel channel;
    private TemplateData templateData;
    private NotificationCreationTime createdAt;
    private List<NotificationRecipient> notificationRecipients;

    /// Constructor
    public Notification(UUID id) {
        super(id);
    }

    public Notification() {
    }

    /// Business logic
    public void editCategory(NotificationCategory category) {
        this.category = category;
        markChanged("category");
    }

    public void editTemplateData(TemplateData templateData) {
        this.templateData = templateData;
        markChanged("templateData");
    }

    public void addRecipient(NotificationRecipient recipient) {
        notificationRecipients.add(recipient);
        markNestedChanged(recipient, "");
        this.addDomainEvent(new NotificationReceivedEvent(
                this.getId(),
                recipient.getId()
        ));
    }

    public void hardDeleteRecipient(UUID recipientId) {
        var recipientToDelete = getRecipient(recipientId);
        markNestedChanged(recipientToDelete, DomainStatus.DELETED.name());
        notificationRecipients.remove(recipientToDelete);
    }

    public void softDeleteNotification(UUID recipientId) {
        var recipientToDelete = getRecipient(recipientId);
        recipientToDelete.softDelete(new NotificationDeletionTime(LocalDateTime.now()));
        markNestedChanged(recipientToDelete, "deletedAt");
    }

    public void readNotification(UUID recipientId) {
        var recipientToRead = getRecipient(recipientId);
        recipientToRead.markAsRead();
        markNestedChanged(recipientToRead, "isRead");
    }

    public void restoreNotification(UUID recipientId) {
        var recipientToRestore = getRecipient(recipientId);
        recipientToRestore.restore();
        markNestedChanged(recipientToRestore, "deletedAt");
    }

    /// Helper
    private NotificationRecipient getRecipient(UUID recipientId) {
        return notificationRecipients.stream()
                .filter(recipient -> recipient.getRecipientId().equals(recipientId))
                .findFirst()
                .orElseThrow(() -> new RecipientNotFoundException(recipientId));
    }

    /// Factory method
    public static Notification create(
            UUID senderId,
            UUID templateId,
            String channel,
            String category,
            Map<String, String> commonTemplateData
    ) {
        Notification notification = new Notification();
        notification.senderId = senderId;
        notification.templateId = templateId;
        notification.notificationRecipients = new ArrayList<>();
        notification.channel = new NotificationChannel(channel);
        notification.createdAt = new NotificationCreationTime(LocalDateTime.now());
        notification.editCategory(new NotificationCategory(category));
        notification.editTemplateData(new TemplateData(commonTemplateData));

        return notification;
    }

    public static Notification reconstruct(UUID id,
                                           UUID templateId,
                                           UUID senderId,
                                           String category,
                                           String channel,
                                           Map<String, String> templateData,
                                           LocalDateTime createdAt,
                                           List<NotificationRecipient> notificationRecipients) {
        Notification notification = new Notification(id);
        notification.senderId = senderId;
        notification.templateId = templateId;
        notification.category = new NotificationCategory(category);
        notification.channel = new NotificationChannel(channel);
        notification.templateData = new TemplateData(templateData);
        notification.createdAt = new NotificationCreationTime(createdAt);
        notification.notificationRecipients = new ArrayList<>(notificationRecipients);
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

    public TemplateData getTemplateData() {
        return templateData;
    }

    public NotificationCreationTime getCreatedAt() {
        return createdAt;
    }

    public List<NotificationRecipient> getNotificationRecipients() {
        return List.copyOf(notificationRecipients);
    }
}
