package studydocs.notification.application.dto.command.notification;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;

@Builder
public record RecipientData(
        UUID recipientId
) {
}
