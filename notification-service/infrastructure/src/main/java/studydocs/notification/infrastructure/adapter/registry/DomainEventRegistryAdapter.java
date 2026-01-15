package studydocs.notification.infrastructure.adapter.registry;

import io.github.domain.event.DomainEvent;
import io.github.domain.port.DomainEventRegistry;
import org.springframework.stereotype.Component;
import studydocs.notification.domain.event.NotificationReceivedEvent;

import java.util.Map;
@Component
public class DomainEventRegistryAdapter implements DomainEventRegistry {
    private final static Map<String, Class<? extends DomainEvent>> domainEventMap = Map.of(
            "NotificationReceivedEvent", NotificationReceivedEvent.class
    );

    @Override
    public Class<? extends DomainEvent> resolve(String type) {
        Class<? extends DomainEvent> clazz = domainEventMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("Unknown event type: " + type);
        }
        return clazz;
    }
}
