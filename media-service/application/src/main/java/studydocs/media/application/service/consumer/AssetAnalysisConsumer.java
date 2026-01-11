package studydocs.media.application.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.helper.PageCounterResolver;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.repository.AssetWriter;
import studydocs.media.domain.vo.TotalPages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetAnalysisConsumer {

    private final AssetWriter assetWriter;
    private final PageCounterResolver pageCounterResolver;
    private final PublishAssetEventPort publishAssetEventPort;

    public void handleUploadRequest(AssetUploadRequestedPayload event) {
        Path tempFile = Path.of(event.tempFilePath());
        UUID assetId = event.assetId();
        log.info("Handling upload request for asset: {}, tempFile: {}", assetId, tempFile);

        try {
            Asset asset = assetWriter.getById(assetId);

            asset.markAsUploading();
            asset = assetWriter.saveAndReturn(asset);

            try (InputStream ignored = new FileInputStream(tempFile.toFile())) {
                var fileExt = FileExtension.fromFileName(event.originalFileName());

                int totalPages;
                try (InputStream pageStream = new FileInputStream(tempFile.toFile())) {
                    totalPages = pageCounterResolver.countPages(fileExt, pageStream);
                }

                asset.setTotalPages(TotalPages.of(totalPages));
                asset = assetWriter.saveAndReturn(asset);

            } catch (Exception e) {
                log.error("Asset analysis failed for assetId: {}", assetId, e);
                asset.failUpload();
                assetWriter.saveAndReturn(asset);
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException ex) {
                    log.warn("Failed to delete temp file (analysis failed): {}", tempFile);
                }
                return;
            }

            publishAssetEventPort.publish(new AssetAnalysisCompletedPayload(
                    assetId,
                    event.tempFilePath(),
                    event.originalFileName(),
                    event.contentType(),
                    event.fileSize()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException ex) {
                System.err.println("Failed to delete temp file (unexpected error): " + tempFile);
            }
        }
    }
}
