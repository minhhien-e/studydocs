package studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update;

import java.util.UUID;

public record MarkAllAsReadInputCommand(UUID recipientId) {
}
