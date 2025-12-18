package studydocs.notification.infrastructure.adapter.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.domain.base.DomainEvent;
import io.github.domain.port.DomainEventSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectMapperAdapter implements DomainEventSerializer {
    private final ObjectMapper objectMapper;

    @Override
    public String serialize(DomainEvent domainEvent) {
        try {
            return objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DomainEvent deserialize(String s) {
        return objectMapper.convertValue(s, DomainEvent.class);
    }
}
