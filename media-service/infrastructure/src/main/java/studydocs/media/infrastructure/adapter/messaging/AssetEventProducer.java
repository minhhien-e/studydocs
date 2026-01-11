package studydocs.media.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.AssetAnalysisCompletedPayload;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;
import studydocs.media.application.dto.payload.AssetUploadFailedPayload;
import studydocs.media.application.dto.payload.AssetUploadedPayload;
import studydocs.media.application.port.out.messaging.EventPublisherPort;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class AssetEventProducer implements PublishAssetEventPort {
    private final EventPublisherPort eventPublisherPort;

    @Override
    public void publish(AssetUploadedPayload event) {
        eventPublisherPort.publish(RabbitMQConfig.NOTIFICATION_EXCHANGE, RabbitMQConfig.UPLOAD_COMPLETED_ROUTING_KEY,
                event);
    }

    @Override
    public void publish(AssetUploadFailedPayload event) {
        eventPublisherPort.publish(RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.ASSET_UPLOAD_FAILED_ROUTING_KEY,
                event);
    }

    @Override
    public void publish(AssetUploadRequestedPayload event) {
        eventPublisherPort.publish(RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.ASSET_UPLOAD_REQUESTED_ROUTING_KEY, event);
    }

    @Override
    public void publish(AssetAnalysisCompletedPayload payload) {
        eventPublisherPort.publish(
                RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.ASSET_ANALYSIS_COMPLETED_ROUTING_KEY,
                payload);
    }

    @Override
    public void publish(AssetDeletionFailedPayload payload) {
        eventPublisherPort.publish(
                RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.ASSET_DELETION_FAILED_ROUTING_KEY,
                payload);
    }

}
