package studydocs.media.application.service.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetUploadedPayload;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.domain.event.AssetUploadedEvent;
import studydocs.media.application.port.in.job.OutboxEventHandler;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetUploadedEventHandler implements OutboxEventHandler {

    private final ObjectMapper objectMapper;
    private final PublishAssetEventPort publishAssetEventPort;

    @Override
    public boolean canHandle(String type) {
        return type.endsWith("AssetUploadedEvent");
    }

    @Override
    public void handle(String payload) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            AssetUploadedEvent domainEvent = objectMapper.readValue(payload, AssetUploadedEvent.class);
            var eventPayload = new AssetUploadedPayload(
                    domainEvent.assetId(),
                    domainEvent.userId());
            publishAssetEventPort.publish(eventPayload);
        } catch (Exception e) {
            log.error("Failed to handle AssetUploadedEvent: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
