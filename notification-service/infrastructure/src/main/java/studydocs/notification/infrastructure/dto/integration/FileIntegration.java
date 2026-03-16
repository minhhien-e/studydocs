package studydocs.notification.infrastructure.dto.integration;

import java.time.LocalDateTime;
import java.util.UUID;

public record FileIntegration(UUID id, String fileName,
                              long fileSize, int totalPage) {
}
