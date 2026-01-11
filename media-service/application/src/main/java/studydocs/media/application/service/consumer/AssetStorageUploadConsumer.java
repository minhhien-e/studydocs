package studydocs.media.application.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.port.out.storage.AssetStoragePort;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.repository.AssetWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetStorageUploadConsumer {

    private final AssetWriter assetWriter;
    private final AssetStoragePort assetStoragePort;

    public void handleAnalysisCompleted(AssetAnalysisCompletedPayload event) {
        Path tempFile = Path.of(event.tempFilePath());
        UUID assetId = event.assetId();

        try {
            Asset assetInitial = assetWriter.getById(assetId);
            java.util.concurrent.atomic.AtomicReference<Asset> assetRef = new java.util.concurrent.atomic.AtomicReference<>(
                    assetInitial);
            AtomicInteger lastSavedProgress = new AtomicInteger(0);

            try {
                var storageLocation = assetStoragePort.upload(
                        tempFile.toFile(),
                        event.originalFileName(),
                        event.fileSize(),
                        (progress) -> {
                            int currentLast = lastSavedProgress.get();
                            if (progress - currentLast >= 10 || progress == 100) {
                                Asset currentAsset = assetRef.get();
                                currentAsset.updateProgress(progress);
                                assetRef.set(assetWriter.saveAndReturn(currentAsset));
                                lastSavedProgress.set(progress);
                            }
                        });

                Asset finalAsset = assetRef.get();
                finalAsset.completeUpload(storageLocation);
                assetWriter.saveAndReturn(finalAsset);

            } catch (Exception e) {
                Asset failedAsset = assetRef.get();
                failedAsset.failUpload();
                assetWriter.saveAndReturn(failedAsset);
            }

        } catch (Exception e) {
            log.error("Error processing storage upload event: {}", e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException e) {
                log.warn("Failed to delete temp file: {}", tempFile);
            }
        }
    }
}
