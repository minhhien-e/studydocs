package studydocs.media.application.port.out.messaging;

public interface EventPublisherPort {
    void publish(String exchange, String routingKey, Object event);
}
