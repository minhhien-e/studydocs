package studydocs.media.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.media.infrastructure.persistence.entity.FileEntity;

import java.util.UUID;

public interface MongoDataFileRepository extends MongoRepository<FileEntity, UUID> {
}
