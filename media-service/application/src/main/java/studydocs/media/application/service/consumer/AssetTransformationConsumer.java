package studydocs.media.application.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetTransformationCompletedPayload;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.dto.transformation.TransformationResult;
import studydocs.media.application.helper.AssetProcessingHelper;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.application.port.out.transformation.AssetTransformationPort;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.policy.AssetTransformationPolicy;
import studydocs.media.domain.repository.AssetWriter;
import studydocs.media.domain.vo.AssetContentType;
import studydocs.media.domain.vo.AssetName;
import studydocs.media.domain.vo.AssetSize;

import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetTransformationConsumer {

    private final AssetWriter assetWriter;
    private final PublishAssetEventPort publishAssetEventPort;
    private final AssetTransformationPort assetTransformationPort;
    private final AssetProcessingHelper assetProcessingHelper;
    private final AssetTransformationPolicy assetTransformationPolicy;

    public void handleUploadRequest(AssetUploadRequestedPayload event) {
        Path currentFile = Path.of(event.tempFilePath());
        UUID assetId = event.assetId();
        log.info("Handling upload request for asset: {}, tempFile: {}", assetId, currentFile);

        try {
            Asset asset = assetWriter.getById(assetId);
            asset.markAsUploading();
            asset = assetWriter.saveAndReturn(asset);

            String fileName = event.originalFileName();
            String contentType = event.contentType();
            long fileSize = event.fileSize();
            FileExtension fileExt = FileExtension.fromFileName(fileName);
            
            try {
                if (assetTransformationPolicy.canTransform(fileExt)) {
                    log.info("Asset {} requires transformation. Extension: {}", assetId, fileExt);
                    
                    TransformationResult result = assetTransformationPort.transform(currentFile, fileName);
                    
                    assetProcessingHelper.cleanup(currentFile);
                    
                    currentFile = result.file();
                    fileName = result.fileName();
                    contentType = result.contentType();
                    fileSize = result.fileSize();
    
                    asset.transformTo(
                            AssetName.of(fileName),
                            AssetSize.of(fileSize),
                            AssetContentType.of(contentType)
                    );
                    assetWriter.save(asset);
                    log.info("Asset {} transformed. New size: {}", assetId, fileSize);
                }
    
                publishAssetEventPort.publish(new AssetTransformationCompletedPayload(
                        assetId,
                        currentFile.toAbsolutePath().toString(),
                        fileName,
                        contentType,
                        fileSize));
                        
            } catch (Exception e) {
                 log.warn("Transformation failed for asset {}, failing process.", assetId, e);
                 throw e; 
            }

        } catch (Exception e) {
            log.error("Unexpected error handling upload request for asset: {}", assetId, e);
            assetProcessingHelper.handleFailure(assetId, e);
            assetProcessingHelper.cleanup(currentFile);
        }
    }
}
