package studydocs.media.application.dto.projection;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FileProjection(UUID id, String fileName,
                             long fileSize, String contentType, int totalPage,
                             PreviewData previewData, String downloadUrl) {

}

