package studydocs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.model.DocumentStats;

import java.util.UUID;

public interface DocumentStatsRepository extends MongoRepository<DocumentStats, UUID> {
}
