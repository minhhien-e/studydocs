package studydocs.dto.projection;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FileProjection(
        UUID id,
        String assetName,
        long size,
        String contentType,
        int totalPages,
        String downloadUrl,
        PreviewDataView previewDataView) {
    public record PreviewDataView(String baseUrl, String key) {
    }
}
