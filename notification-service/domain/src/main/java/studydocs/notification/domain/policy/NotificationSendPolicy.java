package studydocs.notification.domain.policy;

import java.util.List;
import java.util.UUID;

public interface NotificationSendPolicy {
    void ensureCanCreate(
            UUID senderId,
            UUID templateId
    );

    void ensureCanSend(List<UUID> recipientIds);
}
