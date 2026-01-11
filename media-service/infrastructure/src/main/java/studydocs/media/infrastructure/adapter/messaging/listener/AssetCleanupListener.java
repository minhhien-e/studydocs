package studydocs.media.infrastructure.adapter.messaging.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;
import studydocs.media.application.dto.payload.AssetUploadFailedPayload;
import studydocs.media.application.service.consumer.AssetCleanupConsumer;
import studydocs.media.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssetCleanupListener {
    private final AssetCleanupConsumer assetCleanupConsumer;

    @RabbitListener(queues = RabbitMQConfig.ASSET_CLEANUP_QUEUE)
    public void handle(AssetUploadFailedPayload payload) {
        assetCleanupConsumer.handle(payload);
    }

    @RabbitListener(queues = RabbitMQConfig.ASSET_DELETION_FAILED_QUEUE)
    public void handle(AssetDeletionFailedPayload payload) {
        assetCleanupConsumer.handle(payload);
    }
}
