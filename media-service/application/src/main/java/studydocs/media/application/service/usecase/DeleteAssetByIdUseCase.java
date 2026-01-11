package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.DeleteAssetByIdCommand;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;
import studydocs.media.application.port.in.usecase.DeleteAssetByIdUseCasePort;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.application.port.out.storage.AssetStoragePort;
import studydocs.media.domain.repository.AssetWriter;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteAssetByIdUseCase implements DeleteAssetByIdUseCasePort {
    private final AssetWriter assetWriter;
    private final AssetStoragePort assetStoragePort;
    private final PublishAssetEventPort publishAssetEventPort;

    @Override
    public Void execute(DeleteAssetByIdCommand params) {
        var asset = assetWriter.getById(params.assetId());
        try {
            assetStoragePort.delete(asset.getStorageLocation().key(), asset.getStorageLocation().namespace());
        } catch (Exception e) {
            log.warn("Failed to delete asset storage file for assetId: {}. Proceeding to delete metadata. Error: {}",
                    params.assetId(), e.getMessage());
            publishAssetEventPort.publish(new AssetDeletionFailedPayload(
                    asset.getStorageLocation().key(),
                    asset.getStorageLocation().namespace(),
                    e.getMessage()));
        }
        assetWriter.delete(asset);
        return null;
    }
}
