package studydocs.notificationservice.domain.model.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record UploadDocumentEvent(UUID userId, UUID documentId, String documentName, LocalDateTime uploadedAt) {
}
