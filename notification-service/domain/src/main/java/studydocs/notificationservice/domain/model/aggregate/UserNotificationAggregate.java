package studydocs.notificationservice.domain.model.aggregate;

import studydocs.notificationservice.domain.exceptions.entity.notification.EmptyNotificationListException;
import studydocs.notificationservice.domain.exceptions.entity.notification.NotificationNotFoundException;
import studydocs.notificationservice.domain.model.entity.Recipient;

import java.util.*;

public class UserNotificationAggregate {
    private final UUID recipientId;
    private final List<Recipient> notifications = new ArrayList<>();

    public UserNotificationAggregate(UUID userId, List<Recipient> notifications) {
        this.recipientId = userId;
        this.notifications.addAll(notifications);
    }

    public UserNotificationAggregate(UUID userId) {
        this.recipientId = userId;
    }

    public Recipient receiveNotification(UUID notificationId) {
        var recipient = new Recipient(recipientId, notificationId);
        notifications.add(recipient);
        return recipient;
    }

    /// Đánh dấu thông báo đã đọc
    public void markNotificationAsRead(UUID notificationId) {
        var notification = getNotification(notificationId);
        if (notification.isEmpty()) {
            throw new NotificationNotFoundException();
        }
        notification.get().read();
    }

    public void markAllNotificationsAsRead() {
        if (notifications.isEmpty()) {
            throw new EmptyNotificationListException("chưa đọc");
        }
        notifications.forEach(Recipient::read);
    }

    /// Xóa thông báo
    public Recipient softDeleteNotification(UUID notificationId) {
        var notification = getNotification(notificationId);
        if (notification.isEmpty()) {
            throw new NotificationNotFoundException();
        }
        notification.get().delete();
        return notification.get();
    }

    public void hardDeleteNotification(UUID notificationId) {
        var notification = getNotification(notificationId);
        if (notification.isEmpty()) {
            throw new NotificationNotFoundException();
        }
        notifications.remove(notification.get());
    }

    public void hardDeleteAllNotifications() {
        if (notifications.isEmpty()) throw new EmptyNotificationListException("tạm xóa");
        notifications.clear();
    }

    public void softDeleteAllNotifications() {
        if (notifications.isEmpty()) throw new EmptyNotificationListException("");
        notifications.forEach(Recipient::delete);
    }

    /// Khôi phục thông báo
    public void restoreNotification(List<UUID> notificationIdList) {
        notificationIdList.forEach(notificationId -> {
            var notification = getNotification(notificationId);
            if (notification.isEmpty()) {
                throw new NotificationNotFoundException();
            }
            notification.get().restore();
        });
    }

    /// Lấy thông báo
    public Optional<Recipient> getNotification(UUID notificationId) {
        return notifications.stream().filter(n -> n.getNotificationId().equals(notificationId)).findFirst();
    }

    public List<Recipient> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    public UUID getRecipientId() {
        return recipientId;
    }
}
