package studydocs.media.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.FileUploadedPayload;
import studydocs.media.application.port.out.messaging.PublishFileEventPort;
import studydocs.media.domain.port.EventPublisherPort;
import studydocs.media.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class UploadCompletedProducer implements PublishFileEventPort {
    private final EventPublisherPort eventPublisher;

    @Override
    public void publish(FileUploadedPayload event) {
        eventPublisher.publish(RabbitMQConfig.NOTIFICATION_EXCHANGE, RabbitMQConfig.UPLOAD_COMPLETED_ROUTING_KEY, event);
    }
}
