package studydocs.media.domain.aggregate;

import io.github.ddd.core.aggregate.AggregateRoot;
import studydocs.media.domain.enums.AssetStatus;
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

    private AssetStatus status;
    private int uploadProgress;

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
            StorageLocation storageLocation) {
        Asset asset = new Asset(id, 0);
        asset.uploaderId = uploaderId;
        asset.assetName = assetName;
        asset.size = size;
        asset.contentType = contentType;
        asset.totalPages = totalPages != null ? totalPages : TotalPages.of(0);
        asset.storageLocation = storageLocation;
        asset.status = AssetStatus.PENDING;
        asset.uploadProgress = 0;
        asset.creationTime = AssetCreationTime.now();
        return asset;
    }

    public void markAsUploading() {
        this.status = AssetStatus.UPLOADING;
        this.uploadProgress = 0;
    }

    public void updateProgress(int percent) {
        if (this.status != AssetStatus.UPLOADING) {
            return;
        }
        this.uploadProgress = percent;
    }

    public void setTotalPages(TotalPages totalPages) {
        this.totalPages = totalPages;
    }

    public void transformTo(AssetName newName, AssetSize newSize, AssetContentType newType) {
        this.assetName = newName;
        this.size = newSize;
        this.contentType = newType;
    }

    public void completeUpload(StorageLocation location) {
        this.status = AssetStatus.UPLOADED;
        this.uploadProgress = 100;
        this.storageLocation = location;
        this.addDomainEvent(new AssetUploadedEvent(this.getId(), this.uploaderId));
    }

    public void failUpload() {
        this.status = AssetStatus.FAILED;
        this.uploadProgress = 0;
    }

    public void markAsDeleted() {
        this.status = AssetStatus.DELETED;
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
            String resourceType,
            String status,
            Integer uploadProgress) {
        Asset asset = new Asset(id, version);
        asset.uploaderId = uploaderId;
        asset.assetName = AssetName.of(assetName);
        asset.size = AssetSize.of(size);
        asset.contentType = AssetContentType.of(contentType);
        asset.totalPages = TotalPages.of(totalPages);
        asset.creationTime = AssetCreationTime.of(createdAt);
        if (publicId != null && resourceType != null) {
            asset.storageLocation = StorageLocation.of(publicId, resourceType);
        }
        asset.status = status != null ? AssetStatus.valueOf(status) : AssetStatus.PENDING;
        asset.uploadProgress = uploadProgress != null ? uploadProgress : 0;
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

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public int getUploadProgress() {
        return uploadProgress;
    }
}
