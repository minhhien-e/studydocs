package studydocs.media.infrastructure.adapter.repository.asset;

import io.github.ddd.core.specification.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.exception.asset.AssetNotFoundException;
import studydocs.media.domain.repository.AssetWriter;
import studydocs.media.infrastructure.adapter.repository.outbox.OutboxWriterAdapter;
import studydocs.media.infrastructure.mapper.AssetMapper;
import studydocs.media.infrastructure.persistence.entity.AssetEntity;
import studydocs.media.infrastructure.persistence.repository.MongoDataAssetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AssetWriterAdapter implements AssetWriter {
    private final MongoDataAssetRepository mongoDataAssetRepository;
    private final OutboxWriterAdapter outboxWriterAdapter;

    @Override
    @Transactional
    public Asset saveAndReturn(Asset aggregate) {
        // 1. Check if asset exists to determine if this is an Insert or Update
        // Note: For high concurrency inserts of same ID, this might still race, but
        // strict uniqueness is on ID.
        boolean exists = mongoDataAssetRepository.existsById(aggregate.getId());

        // 2. Save Asset
        AssetEntity entity = AssetMapper.toEntity(aggregate, !exists);
        AssetEntity savedEntity = mongoDataAssetRepository.save(entity);

        try {
            // 2. Save Outbox Events
            int eventCount = aggregate.getDomainEvents() != null ? aggregate.getDomainEvents().size() : 0;
            log.info("Saving Asset {}. Event Count: {}", aggregate.getId(), eventCount);

            outboxWriterAdapter.saveAll(aggregate.getId(), aggregate.getDomainEvents());
            aggregate.clearDomainEvents();
        } catch (Exception e) {
            log.error("Failed to save outbox events for asset {}. Transaction will rollback.", aggregate.getId(), e);
            throw e;
        }

        return AssetMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public void save(Asset aggregate) {
        saveAndReturn(aggregate);
    }

    @Override
    public Optional<Asset> findById(UUID id) {
        return mongoDataAssetRepository.findById(id)
                .map(AssetMapper::toDomain);
    }

    @Override
    public Asset getById(UUID id) {
        return findById(id).orElseThrow(() -> new AssetNotFoundException(id));
    }

    @Override
    public List<Asset> findAll(Specification<Asset> spec) {
        return mongoDataAssetRepository.findAll().stream()
                .map(AssetMapper::toDomain)
                .filter(spec::isSatisfiedBy)
                .toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return mongoDataAssetRepository.existsById(id);
    }

    @Override
    public boolean exists(Specification<Asset> spec) {
        return mongoDataAssetRepository.findAll().stream()
                .map(AssetMapper::toDomain)
                .anyMatch(spec::isSatisfiedBy);
    }

    @Override
    public void delete(Asset aggregate) {
        deleteById(aggregate.getId());
    }

    @Override
    public void deleteById(UUID id) {
        if (!mongoDataAssetRepository.existsById(id)) {
            throw new AssetNotFoundException(id);
        }
        mongoDataAssetRepository.deleteById(id);
    }
}
