package studydocs.media.infrastructure.mapper;

import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.vo.*;
import studydocs.media.infrastructure.persistence.entity.AssetEntity;

public class AssetMapper {
    private AssetMapper() {
    }

    public static Asset toDomain(AssetEntity entity) {
        if (entity == null)
            return null;
        return Asset.reconstruct(
                entity.getId(),
                entity.getVersion(),
                entity.getUploaderId(),
                entity.getAssetName(),
                entity.getSize(),
                entity.getContentType(),
                entity.getTotalPages(),
                entity.getCreatedAt(),
                entity.getPublicId(),
                entity.getResourceType(),
                entity.getStatus(),
                entity.getUploadProgress());
    }

    public static AssetEntity toEntity(Asset domain, boolean isNew) {
        return AssetEntity.builder()
                .id(domain.getId())
                .uploaderId(domain.getUploaderId())
                .assetName(domain.getAssetName().value())
                .size(domain.getSize().value())
                .contentType(domain.getContentType().value())
                .totalPages(domain.getTotalPages().value())
                .publicId(domain.getStorageLocation() != null ? domain.getStorageLocation().key() : null)
                .resourceType(domain.getStorageLocation() != null ? domain.getStorageLocation().namespace() : null)
                .status(domain.getStatus().name())
                .uploadProgress(domain.getUploadProgress())
                .createdAt(domain.getCreationTime().value())
                .version(isNew ? null : domain.getVersion())
                .build();
    }

    public static AssetEntity toEntity(Asset domain) {
        // Default behavior: assume new if version is 0 (legacy behavior, but dangerous)
        // Or better: deprecate this and force usage of the boolean.
        // For now, let's delegate with the old logic to match existing calls we might
        // miss,
        // but typically we should switch to the explicit one.
        return toEntity(domain, domain.getVersion() == 0);
    }
}
