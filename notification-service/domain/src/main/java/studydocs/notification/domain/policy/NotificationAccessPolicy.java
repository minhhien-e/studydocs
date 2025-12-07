package studydocs.notification.domain.policy;

import studydocs.notification.domain.aggregate.Notification;
import java.util.UUID;

public interface NotificationAccessPolicy {
    void checkCanAccess(Notification notification, UUID recipientId);
}
