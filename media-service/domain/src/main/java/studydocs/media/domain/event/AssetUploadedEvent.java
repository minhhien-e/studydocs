package studydocs.media.domain.event;

import io.github.ddd.core.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssetUploadedEvent(UUID assetId,
                                 UUID userId) implements DomainEvent {
    @Override
    public UUID eventId() {
        return UUID.randomUUID();
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public String type() {
        return "AssetUploadedEvent";
    }
}
