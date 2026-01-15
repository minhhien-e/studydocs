package studydocs.media.application.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetTransformationCompletedPayload;
import studydocs.media.application.helper.AssetProcessingHelper;
import studydocs.media.application.helper.PageCounterResolver;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.repository.AssetWriter;
import studydocs.media.domain.vo.TotalPages;

import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetPageCountingConsumer {

    private final AssetWriter assetWriter;
    private final PageCounterResolver pageCounterResolver;
    private final PublishAssetEventPort publishAssetEventPort;
    private final AssetProcessingHelper assetProcessingHelper;

    public void handleTransformationCompleted(AssetTransformationCompletedPayload event) {
        UUID assetId = event.assetId();
        Path fileToCount = Path.of(event.tempFilePath());
        String fileName = event.fileName();

        log.info("Handling page counting for asset: {}", assetId);

        try {
            Asset asset = assetWriter.getById(assetId);

            try {
                FileExtension fileExt = FileExtension.fromFileName(fileName);
                int totalPages = pageCounterResolver.countPages(fileExt, fileToCount);
                asset.setTotalPages(TotalPages.of(totalPages));

                assetWriter.save(asset);
                log.info("Page counting completed for asset {}: {} pages", assetId, totalPages);

                publishAssetEventPort.publish(new AssetAnalysisCompletedPayload(
                        assetId,
                        event.tempFilePath(),
                        event.fileName(),
                        event.contentType(),
                        event.fileSize()));

            } catch (Exception e) {
                assetProcessingHelper.handleFailure(assetId, e);
                assetProcessingHelper.cleanup(fileToCount);
            }
        } catch (Exception e) {
            log.error("Unexpected error handling page counting for asset: {}", assetId, e);
            assetProcessingHelper.cleanup(fileToCount);
        }
    }
}
