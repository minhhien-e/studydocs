package studydocs.media.infrastructure.adapter.messaging.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.service.consumer.AssetTransformationConsumer;
import studydocs.media.application.service.consumer.AssetStorageUploadConsumer;
import studydocs.media.infrastructure.config.RabbitMQConfig;

import studydocs.media.application.dto.payload.AssetTransformationCompletedPayload;
import studydocs.media.application.service.consumer.AssetPageCountingConsumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class AssetProcessListener {

    private final AssetTransformationConsumer assetTransformationConsumer;
    private final AssetPageCountingConsumer assetPageCountingConsumer;
    private final AssetStorageUploadConsumer assetStorageUploadConsumer;

    @RabbitListener(queues = RabbitMQConfig.ASSET_UPLOAD_REQUESTED_QUEUE)
    public void onUploadRequested(AssetUploadRequestedPayload event) {
        assetTransformationConsumer.handleUploadRequest(event);
    }

    @RabbitListener(queues = RabbitMQConfig.ASSET_TRANSFORMATION_COMPLETED_QUEUE)
    public void onTransformationCompleted(AssetTransformationCompletedPayload event) {
        assetPageCountingConsumer.handleTransformationCompleted(event);
    }

    @RabbitListener(queues = RabbitMQConfig.ASSET_ANALYSIS_COMPLETED_QUEUE)
    public void onAnalysisCompleted(AssetAnalysisCompletedPayload event) {
        assetStorageUploadConsumer.handleAnalysisCompleted(event);
    }
}
