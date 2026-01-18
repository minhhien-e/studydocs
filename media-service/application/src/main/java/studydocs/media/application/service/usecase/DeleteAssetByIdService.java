package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.DeleteAssetByIdCommand;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;
import studydocs.media.application.port.in.usecase.DeleteAssetByIdUseCase;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.application.port.out.storage.AssetStoragePort;
import studydocs.media.domain.repository.AssetWriter;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteAssetByIdService implements DeleteAssetByIdUseCase {
    private final AssetWriter assetWriter;
    private final AssetStoragePort assetStoragePort;
    private final PublishAssetEventPort publishAssetEventPort;

    @Override
    public Void execute(DeleteAssetByIdCommand params) {
        log.info("Request to delete asset: {}", params.assetId());
        var asset = assetWriter.getById(params.assetId());
        
        // Soft delete: Mark as DELETED and save
        asset.markAsDeleted();
        
        try {
            assetWriter.save(asset);
            log.info("Asset {} marked as DELETED in database.", params.assetId());
        } catch (Exception e) {
            log.error("Failed to mark asset {} as DELETED: {}", params.assetId(), e.getMessage());
            throw new studydocs.media.domain.exception.asset.DeleteFailedException("Failed to delete asset metadata: " + e.getMessage());
        }
        return null;
    }
}
