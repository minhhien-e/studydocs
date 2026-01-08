package studydocs.media.application.dto.payload;

import java.util.UUID;

public record FileUploadedPayload(UUID fileId, UUID userId) {
}
