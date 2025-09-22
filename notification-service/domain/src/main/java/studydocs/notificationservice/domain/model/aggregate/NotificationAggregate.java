package studydocs.notificationservice.domain.model.aggregate;

import studydocs.notificationservice.domain.model.entity.Notification;
import studydocs.notificationservice.domain.model.entity.Recipient;

public class NotificationAggregate {
    private final Notification notification;
    private final Recipient recipient;

    public NotificationAggregate(Notification notification, Recipient recipient) {
        this.notification = notification;
        this.recipient = recipient;
    }

    public Notification getNotification() {
        return notification;
    }

    public Recipient getRecipient() {
        return recipient;
    }
}

