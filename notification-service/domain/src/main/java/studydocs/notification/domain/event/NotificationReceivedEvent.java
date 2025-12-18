package studydocs.notification.domain.event;

import io.github.domain.base.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;
public record NotificationReceivedEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID notificationId,
        UUID notificationRecipientId
) implements DomainEvent {

    public NotificationReceivedEvent(UUID notificationId, UUID notificationRecipientId) {
        this(UUID.randomUUID(), LocalDateTime.now(), notificationId, notificationRecipientId);
    }

    @Override
    public String type() {
        return "NotificationReceivedEvent";
    }
}
