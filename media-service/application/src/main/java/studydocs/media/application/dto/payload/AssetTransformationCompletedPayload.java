package studydocs.media.application.dto.payload;

import java.util.UUID;

public record AssetTransformationCompletedPayload(
    UUID assetId,
    String tempFilePath,
    String fileName,
    String contentType,
    long fileSize
) {
}
