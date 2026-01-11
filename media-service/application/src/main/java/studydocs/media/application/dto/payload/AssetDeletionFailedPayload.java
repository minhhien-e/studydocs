package studydocs.media.application.dto.payload;

public record AssetDeletionFailedPayload(
        String storageKey,
        String storageNamespace,
        String error) {
}
