package studydocs.media.application.dto.projection;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AssetProjection(UUID id, String assetName,
                             long size, String contentType, int totalPages,
                             PreviewData previewData, String downloadUrl,
                             String status, int uploadProgress) {

}
