package studydocs.media.application.dto.payload;

import java.util.UUID;

public record AssetUploadedPayload(UUID assetId, UUID userId) {
}
