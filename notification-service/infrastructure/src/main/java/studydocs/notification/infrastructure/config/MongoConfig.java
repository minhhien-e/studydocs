package studydocs.notification.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "studydocs.notification.infrastructure.persistence.repository")
public class MongoConfig {
}
