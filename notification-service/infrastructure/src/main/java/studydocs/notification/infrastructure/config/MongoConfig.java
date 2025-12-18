package studydocs.notification.infrastructure.config;

import io.github.infrastructure.mongo.helper.MongoEntityWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
        "studydocs.notification.infrastructure.persistence.repository"
})
public class MongoConfig {
    @Bean
    public MongoEntityWriter mongoEntityWriter(MongoTemplate mongoTemplate) {
        return new MongoEntityWriter(mongoTemplate);
    }
}
