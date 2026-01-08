package studydocs.media.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import studydocs.media.domain.port.EventPublisherPort;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQEventPublisher implements EventPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(String exchange, String routingKey, Object event) {
        rabbitTemplate.convertAndSend(exchange,
                routingKey,
                event);
    }
}
