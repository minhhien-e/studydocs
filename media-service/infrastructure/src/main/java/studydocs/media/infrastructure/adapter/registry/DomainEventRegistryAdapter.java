package studydocs.media.infrastructure.adapter.registry;

import io.github.domain.event.DomainEvent;
import io.github.domain.port.DomainEventRegistry;
import org.springframework.stereotype.Component;
import studydocs.media.domain.event.FileUploadedEvent;

import java.util.Map;
@Component
public class DomainEventRegistryAdapter implements DomainEventRegistry {
    private final static Map<String, Class<? extends DomainEvent>> domainEventMap = Map.of(
            "FileUploadedEvent", FileUploadedEvent.class
    );
    @Override
    public Class<? extends DomainEvent> resolve(String type) {
        return domainEventMap.get(type);
    }
}
