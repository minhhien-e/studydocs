package studydocs.notification.infrastructure.job;

import io.github.domain.entity.Outbox;
import io.github.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.NotificationReceivePayload;
import studydocs.notification.application.port.out.messaging.PublishNotificationEventPort;
import studydocs.notification.domain.event.NotificationReceivedEvent;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxJob {
    private final OutboxRepository outboxRepository;
    private final PublishNotificationEventPort publishNotificationEventPort;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        int limit = 20;
        List<Outbox> events = outboxRepository.findPendingEvents(limit);
        events.forEach(this::processEvent);
    }

    private void processEvent(Outbox event) {
        try {
            if (event.getPayload() instanceof NotificationReceivedEvent domainEvent) {
                var payload = new NotificationReceivePayload(domainEvent.notificationId(), domainEvent.notificationRecipientId());
                publishNotificationEventPort.publish(payload);
            }
            outboxRepository.markAsProcessed(event.getId());
        } catch (Exception e) {
            outboxRepository.markAsFailed(event.getId(), e.getMessage());
        }
    }
}
