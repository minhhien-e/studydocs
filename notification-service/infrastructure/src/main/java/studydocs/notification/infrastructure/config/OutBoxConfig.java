package studydocs.notification.infrastructure.config;

import io.github.domain.repository.OutboxRepository;
import io.github.infrastructure.mongo.repository.MongoOutboxRepository;
import io.github.infrastructure.mongo.repository.OutBoxMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class OutBoxConfig {
    @Bean
    public OutboxRepository outboxRepository(MongoTemplate mongoTemplate, OutBoxMongoRepository outboxMongoRepository) {
        return new MongoOutboxRepository(outboxMongoRepository, mongoTemplate);
    }
}
