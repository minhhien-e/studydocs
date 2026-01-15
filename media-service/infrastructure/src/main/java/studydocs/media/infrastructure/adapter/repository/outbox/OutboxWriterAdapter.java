package studydocs.media.infrastructure.adapter.repository.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ddd.core.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import studydocs.media.domain.enums.OutboxStatus;
import studydocs.media.domain.vo.OutboxMessage;
import studydocs.media.infrastructure.persistence.entity.OutboxEntity;
import studydocs.media.infrastructure.persistence.repository.MongoOutboxRepository;

import studydocs.media.domain.repository.OutboxWriter;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxWriterAdapter implements OutboxWriter {
    private final MongoOutboxRepository mongoOutboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveAll(UUID aggregateId, List<DomainEvent> events) {
        if (events == null || events.isEmpty()) {
            return;
        }

        System.out.println(
                "DEBUG: OutboxWriterAdapter.saveAll called for " + aggregateId + " with " + events.size() + " events.");

        var outboxEntities = events.stream()
                .map(event -> {
                    try {
                        return OutboxEntity.builder()
                                .id(UUID.randomUUID())
                                .aggregateType("Asset")
                                .aggregateId(aggregateId)
                                .type(event.getClass().getName())
                                .payload(objectMapper.writeValueAsString(event))
                                .status(OutboxStatus.PENDING.name())
                                .build();
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to serialize domain event", e);
                    }
                })
                .toList();

        mongoOutboxRepository.saveAll(outboxEntities);
    }

    @Override
    public List<OutboxMessage> findPendingEvents(int limit) {
        return mongoOutboxRepository
                .findByStatus(OutboxStatus.PENDING.name(), PageRequest.of(0, limit))
                .getContent()
                .stream()
                .map(entity -> new OutboxMessage(
                        entity.getId(),
                        entity.getType(),
                        entity.getPayload()))
                .toList();
    }

    @Override
    public void markAsProcessed(UUID id) {
        var entity = mongoOutboxRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setStatus(OutboxStatus.PROCESSED.name());
            mongoOutboxRepository.save(entity);
        }
    }

    @Override
    public void delete(UUID id) {
        // Soft delete implies logic is handled via markAsProcessed,
        // but if delete is called explicitly, we still perform it or check intent.
        // For now, let's keep it as physical delete but the caller should use
        // markAsProcessed.
        mongoOutboxRepository.deleteById(id);
    }
}
