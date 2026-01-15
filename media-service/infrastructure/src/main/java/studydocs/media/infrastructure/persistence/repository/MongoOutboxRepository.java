package studydocs.media.infrastructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.media.infrastructure.persistence.entity.OutboxEntity;

import java.util.UUID;

public interface MongoOutboxRepository extends MongoRepository<OutboxEntity, UUID> {
    Page<OutboxEntity> findByStatus(String status, Pageable pageable);
}
