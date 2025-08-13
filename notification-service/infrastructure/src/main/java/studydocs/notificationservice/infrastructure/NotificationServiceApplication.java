package studydocs.notificationservice.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import studydocs.notificationservice.infrastructure.outbound.rabbitmq.properties.RabbitMQProperties;

@SpringBootApplication(scanBasePackages = "studydocs.notificationservice")
@EnableMongoAuditing
@EnableConfigurationProperties({
        RabbitMQProperties.class
})
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
