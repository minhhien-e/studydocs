package studydocs.notification.infrastructure.job;

import io.github.domain.entity.Outbox;
import io.github.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.out.messaging.NotificationMessagePort;
import studydocs.notification.application.port.out.repository.LockingOutboxRepository;
import studydocs.notification.domain.event.NotificationReceivedEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxJob {
    private final OutboxRepository outboxRepository;
    private final NotificationMessagePort notificationMessagePort;
    private final LockingOutboxRepository lockingOutboxRepository;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        int limit = 20;
        int processedCount = 0;

        while (processedCount < limit) {
            Outbox event = lockingOutboxRepository.findAndLockNextEvent();
            if (event == null) {
                break;
            }

            try {
                processEvent(event);
            } catch (Exception e) {
                log.error("Failed to process outbox event: {}", event.getId(), e);
            }
            processedCount++;
        }
    }

    private void processEvent(Outbox event) {
        try {
            if (event.getPayload() instanceof NotificationReceivedEvent) {
                notificationMessagePort.publish((NotificationReceivedEvent) event.getPayload());
            } else {
                log.warn("Unknown event type: {}", event.getType());
            }
            outboxRepository.markAsProcessed(event.getId());
        } catch (Exception e) {
            log.error("Error processing event payload", e);
            outboxRepository.markAsFailed(event.getId(), e.getMessage());
        }
    }
}
