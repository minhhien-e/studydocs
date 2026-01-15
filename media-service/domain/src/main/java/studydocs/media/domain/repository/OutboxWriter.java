package studydocs.media.domain.repository;

import io.github.ddd.core.event.DomainEvent;
import java.util.List;
import java.util.UUID;

import studydocs.media.domain.vo.OutboxMessage;

public interface OutboxWriter {
    void saveAll(UUID aggregateId, List<DomainEvent> events);

    List<OutboxMessage> findPendingEvents(int limit);

    void markAsProcessed(UUID id);

    void delete(UUID id);
}
