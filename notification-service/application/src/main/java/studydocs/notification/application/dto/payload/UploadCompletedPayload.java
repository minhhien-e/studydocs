package studydocs.notification.application.dto.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record UploadCompletedPayload(UUID uploadId,
                                    UUID userId,
                                    String fileName,
                                    long fileSize,
                                    LocalDateTime completedAt) {
}
