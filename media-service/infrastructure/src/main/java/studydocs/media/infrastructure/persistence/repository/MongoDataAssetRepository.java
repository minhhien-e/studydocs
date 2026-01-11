package studydocs.media.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.media.infrastructure.persistence.entity.AssetEntity;

import java.util.UUID;

public interface MongoDataAssetRepository extends MongoRepository<AssetEntity, UUID> {
}
