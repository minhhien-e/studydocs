package studydocs.media.infrastructure.adapter.messaging.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.service.consumer.AssetAnalysisConsumer;
import studydocs.media.application.service.consumer.AssetStorageUploadConsumer;
import studydocs.media.infrastructure.config.RabbitMQConfig;

@Component
@Slf4j
@RequiredArgsConstructor
public class AssetProcessListener {

    private final AssetAnalysisConsumer assetAnalysisConsumer;
    private final AssetStorageUploadConsumer assetStorageUploadConsumer;

    @RabbitListener(queues = RabbitMQConfig.ASSET_UPLOAD_REQUESTED_QUEUE)
    public void onUploadRequested(AssetUploadRequestedPayload event) {
        assetAnalysisConsumer.handleUploadRequest(event);
    }

    @RabbitListener(queues = RabbitMQConfig.ASSET_ANALYSIS_COMPLETED_QUEUE)
    public void onAnalysisCompleted(AssetAnalysisCompletedPayload event) {
        assetStorageUploadConsumer.handleAnalysisCompleted(event);
    }
}
