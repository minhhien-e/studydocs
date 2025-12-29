package studydocs.notification.application.dto.payload;

import java.util.UUID;

public record UploadCompletedPayload(UUID fileId, UUID userId) {
}
