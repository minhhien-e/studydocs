package studydocs.media.application.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import studydocs.media.application.dto.payload.AssetDeletionFailedPayload;
import studydocs.media.application.dto.payload.AssetUploadFailedPayload;
import studydocs.media.application.port.out.storage.AssetStoragePort;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetCleanupConsumer {
    private final AssetStoragePort assetStoragePort;

    public void handle(AssetUploadFailedPayload payload) {
        assetStoragePort.delete(payload.storageKey(), payload.storageNamespace());
    }

    public void handle(AssetDeletionFailedPayload payload) {
        assetStoragePort.delete(payload.storageKey(), payload.storageNamespace());
    }
}
