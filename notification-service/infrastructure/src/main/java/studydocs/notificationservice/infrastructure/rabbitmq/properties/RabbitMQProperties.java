package studydocs.notificationservice.infrastructure.rabbitmq.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMQProperties {
    private String exchange;
    private List<Map<String, QueueConfig>> queue;

    @Data
    public static class QueueConfig {
        private String name;
        private List<String> routingKey;
        private boolean durable = true;
        private boolean exclusive = false;
        private boolean autoDelete = false;
    }

}
