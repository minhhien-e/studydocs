package studydocs.notification.application.port.out.messaging;

import studydocs.notification.domain.event.NotificationReceivedEvent;

public interface NotificationMessagePort {
    void publish(NotificationReceivedEvent event);
}
