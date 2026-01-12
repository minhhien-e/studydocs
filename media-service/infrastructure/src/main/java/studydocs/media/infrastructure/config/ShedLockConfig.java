package studydocs.media.infrastructure.config;

import com.mongodb.client.MongoDatabase;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;

@Configuration
public class ShedLockConfig {

    @Bean
    public LockProvider lockProvider(MongoDatabaseFactory mongoDatabaseFactory) {
        MongoDatabase mongoDatabase = mongoDatabaseFactory.getMongoDatabase();
        return new MongoLockProvider(mongoDatabase);
    }
}
