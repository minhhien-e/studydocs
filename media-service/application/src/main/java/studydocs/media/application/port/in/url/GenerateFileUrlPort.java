package studydocs.media.application.port.in.url;

import studydocs.media.application.dto.payload.GenerateDownloadUrlPayload;
import studydocs.media.application.dto.payload.GeneratePreviewUrlPayLoad;
import studydocs.media.application.dto.projection.PreviewData;

public interface GenerateFileUrlPort {
    String generateDownloadUrl(GenerateDownloadUrlPayload payload);
    PreviewData generatePreviewUrl(GeneratePreviewUrlPayLoad payload);
}
