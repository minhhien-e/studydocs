package studydocs.media.api.dto.view;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FileView(UUID id, String fileName,
                       long fileSize, String contentType, int totalPage,
                       PreviewDataView previewDataView, String downloadUrl) {

}
