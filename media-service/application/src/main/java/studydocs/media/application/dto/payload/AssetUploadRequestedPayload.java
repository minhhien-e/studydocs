package studydocs.media.application.dto.payload;

import java.util.UUID;

public record AssetUploadRequestedPayload(
        UUID assetId,
        String tempFilePath,
        String originalFileName,
        String contentType,
        long fileSize
) {
}
