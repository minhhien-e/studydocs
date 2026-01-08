package studydocs.media.domain.port;

public interface EventPublisherPort {
    void publish(String exchange, String routingKey, Object event);
}
