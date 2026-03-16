package studydocs.notification.infrastructure.config;

import io.github.infrastructure.mongo.helper.MongoHelper;
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
    public MongoHelper mongoHelper(MongoTemplate mongoTemplate) {
        return new MongoHelper(mongoTemplate);
    }
}
