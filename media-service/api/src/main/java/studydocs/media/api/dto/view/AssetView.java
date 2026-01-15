package studydocs.media.api.dto.view;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AssetView(UUID id, String assetName,
        long size, String contentType, int totalPages,
        PreviewDataView previewDataView, String downloadUrl,
        String status, int uploadProgress) {
}
