package studydocs.media.infrastructure.job;

import io.github.domain.entity.Outbox;
import io.github.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.FileUploadedPayload;
import studydocs.media.application.port.out.messaging.PublishFileEventPort;
import studydocs.media.domain.event.FileUploadedEvent;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxJob {
    private final OutboxRepository outboxRepository;
    private final PublishFileEventPort uploadFileEventPort;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        int limit = 20;
        List<Outbox> events = outboxRepository.findPendingEvents(limit);
        events.forEach(this::processEvent);
    }

    private void processEvent(Outbox event) {
        try {
            if (event.getPayload() instanceof FileUploadedEvent domainEvent) {
                var payload = new FileUploadedPayload(domainEvent.fileId(), domainEvent.userId());
                uploadFileEventPort.publish(payload);
            }
            outboxRepository.markAsProcessed(event.getId());
        } catch (Exception e) {
            outboxRepository.markAsFailed(event.getId(), e.getMessage());
        }
    }
}
