package studydocs.media.application.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.repository.AssetWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssetProcessingHelper {

    private final AssetWriter assetWriter;

    public void cleanup(Path tempFile) {
        if (tempFile == null) {
            return;
        }
        try {
            Files.deleteIfExists(tempFile);
        } catch (IOException ex) {
            log.warn("Failed to delete temp file: {}", tempFile);
        }
    }

    public void handleFailure(UUID assetId, Exception e) {
        log.error("Asset processing failed for assetId: {}", assetId, e);
        try {
            Asset asset = assetWriter.getById(assetId);
            if (asset != null) {
                asset.failUpload();
                assetWriter.saveAndReturn(asset);
            }
        } catch (Exception ex) {
            log.error("Failed to update asset status to FAILED for assetId: {}", assetId, ex);
        }
    }
}
