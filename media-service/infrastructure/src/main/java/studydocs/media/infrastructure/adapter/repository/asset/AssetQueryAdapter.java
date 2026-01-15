package studydocs.media.infrastructure.adapter.repository.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.media.application.dto.payload.GenerateDownloadUrlPayload;
import studydocs.media.application.dto.payload.GeneratePreviewUrlPayLoad;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.port.in.url.GenerateAssetUrlPort;
import studydocs.media.application.port.out.repository.AssetQueries;
import studydocs.media.domain.exception.asset.AssetNotFoundException;
import studydocs.media.infrastructure.persistence.repository.MongoDataAssetRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AssetQueryAdapter implements AssetQueries {
    private final MongoDataAssetRepository repository;
    private final GenerateAssetUrlPort generateFileUrlPort;

    @Override
    public AssetProjection getById(UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new AssetNotFoundException(id));

        return AssetProjection.builder()
                .id(entity.getId())
                .assetName(entity.getAssetName())
                .size(entity.getSize())
                .totalPages(entity.getTotalPages())
                .contentType(entity.getContentType())
                .status(entity.getStatus())
                .uploadProgress(entity.getUploadProgress() != null ? entity.getUploadProgress() : 0)
                .downloadUrl(generateFileUrlPort.generateDownloadUrl(new GenerateDownloadUrlPayload(entity.getId())))
                .previewData(generateFileUrlPort.generatePreviewUrl(new GeneratePreviewUrlPayLoad(entity.getId())))
                .build();
    }
}
