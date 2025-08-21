package studydocs.notificationservice.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import studydocs.notificationservice.infrastructure.outbound.rabbitmq.properties.RabbitMQProperties;

@SpringBootApplication(scanBasePackages = {"studydocs.notificationservice.application",
        "studydocs.notificationservice.infrastructure"})
@EnableMongoAuditing
@EnableConfigurationProperties({
        RabbitMQProperties.class
})
@EnableScheduling
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
