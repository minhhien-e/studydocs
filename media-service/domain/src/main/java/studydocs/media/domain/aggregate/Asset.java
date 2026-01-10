package studydocs.media.domain.aggregate;

import io.github.ddd.core.aggregate.AggregateRoot;
import studydocs.media.domain.event.AssetUploadedEvent;
import studydocs.media.domain.vo.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class Asset extends AggregateRoot<UUID> {
    private UUID uploaderId;
    private AssetName assetName;
    private AssetSize size;
    private AssetContentType contentType;
    private TotalPages totalPages;
    private AssetCreationTime creationTime;

    private StorageLocation storageLocation;


    /// Constructor
    private Asset(UUID id, long version) {
        super(id, version);
    }


    /// Factory method
    public static Asset create(
            UUID id,
            UUID uploaderId,
            AssetName assetName,
            AssetSize size,
            AssetContentType contentType,
            TotalPages totalPages,
            StorageLocation storageLocation
    ) {
        Asset asset = new Asset(id, 0);
        asset.uploaderId = uploaderId;
        asset.assetName = assetName;
        asset.size = size;
        asset.contentType = contentType;
        asset.totalPages = totalPages;
        asset.storageLocation = storageLocation;
        asset.creationTime = AssetCreationTime.now();

        asset.addDomainEvent(new AssetUploadedEvent(
                asset.getId(),
                asset.uploaderId
        ));
        return asset;
    }

    public static Asset reconstruct(
            UUID id,
            long version,
            UUID uploaderId,
            String assetName,
            Long size,
            String contentType,
            Integer totalPages,
            LocalDateTime createdAt,
            String publicId,
            String resourceType
    ) {
        Asset asset = new Asset(id, version);
        asset.uploaderId = uploaderId;
        asset.assetName = AssetName.of(assetName);
        asset.size = AssetSize.of(size);
        asset.contentType = AssetContentType.of(contentType);
        asset.totalPages = TotalPages.of(totalPages);
        asset.creationTime = AssetCreationTime.of(createdAt);
        asset.storageLocation = StorageLocation.of(publicId, resourceType);
        return asset;
    }

    /// Getters
    public UUID getUploaderId() {
        return uploaderId;
    }

    public AssetName getAssetName() {
        return assetName;
    }

    public AssetSize getSize() {
        return size;
    }

    public AssetContentType getContentType() {
        return contentType;
    }

    public TotalPages getTotalPages() {
        return totalPages;
    }

    public AssetCreationTime getCreationTime() {
        return creationTime;
    }

    public String getPublicId() {
        return storageLocation.key();
    }

    public String getResourceType() {
        return storageLocation.namespace();
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }
}
