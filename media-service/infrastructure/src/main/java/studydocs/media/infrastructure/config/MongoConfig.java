package studydocs.media.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "studydocs.media.infrastructure.persistence.repository")
public class MongoConfig {
}
