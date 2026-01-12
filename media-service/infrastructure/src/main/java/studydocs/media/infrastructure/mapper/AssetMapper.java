package studydocs.media.infrastructure.mapper;

import studydocs.media.domain.aggregate.Asset;
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

}
