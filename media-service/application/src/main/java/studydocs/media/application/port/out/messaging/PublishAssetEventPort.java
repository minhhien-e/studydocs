package studydocs.media.application.port.out.messaging;

import studydocs.media.application.dto.payload.AssetUploadFailedPayload;
import studydocs.media.application.dto.payload.AssetUploadedPayload;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;

public interface PublishAssetEventPort {
    void publish(AssetUploadedPayload event);

    void publish(AssetUploadFailedPayload event);

    void publish(AssetUploadRequestedPayload event);

    void publish(AssetAnalysisCompletedPayload event);

    void publish(AssetDeletionFailedPayload event);
}
