package studydocs.notificationservice.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import studydocs.notificationservice.infrastructure.rabbitmq.properties.RabbitMQProperties;

@SpringBootApplication(scanBasePackages = {
        "studydocs.notificationservice.domain",
        "studydocs.notificationservice.infrastructure",
        "studydocs.notificationservice.application",
        "studydocs.notificationservice.adapter"
})
@EnableMongoRepositories(basePackages = "studydocs.notificationservice.infrastructure.mongo.repository")
@EntityScan(basePackages = "studydocs.notificationservice.infrastructure.mongo.document")
@EnableMongoAuditing
@EnableConfigurationProperties({
        RabbitMQProperties.class
})
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
