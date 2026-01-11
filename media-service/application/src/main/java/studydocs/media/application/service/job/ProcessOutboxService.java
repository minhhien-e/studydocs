package studydocs.media.application.service.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.port.in.job.OutboxEventHandler;
import studydocs.media.application.port.in.job.ProcessOutboxUseCase;
import studydocs.media.domain.repository.OutboxWriter;
import studydocs.media.domain.vo.OutboxMessage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessOutboxService implements ProcessOutboxUseCase {

    private final OutboxWriter outboxWriter;
    private final List<OutboxEventHandler> eventHandlers;

    @org.springframework.beans.factory.annotation.Value("${app.outbox.batch-size:20}")
    private int batchSize;

    @Override
    public void processOutbox() {
        List<OutboxMessage> events = outboxWriter.findPendingEvents(batchSize);
        events.forEach(this::processEvent);
    }

    private void processEvent(OutboxMessage event) {
        try {
            String typeName = event.type();

            var handler = eventHandlers.stream()
                    .filter(h -> h.canHandle(typeName))
                    .findFirst();

            if (handler.isPresent()) {
                handler.get().handle(event.payload());
            } else {
                log.warn("Unknown event type in Outbox: {}", typeName);
            }

            outboxWriter.markAsProcessed(event.id());
        } catch (Exception e) {
            log.error("Failed to process outbox event: {}", event.id(), e);
        }
    }
}
