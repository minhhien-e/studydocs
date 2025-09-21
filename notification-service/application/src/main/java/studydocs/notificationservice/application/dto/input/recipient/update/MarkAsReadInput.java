package studydocs.notificationservice.application.dto.input.recipient.update;

import java.util.UUID;

public record MarkAsReadInput(UUID notificationId, UUID recipientId) {
}
