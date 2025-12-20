package studydocs.notification.api.dto.request.notification;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public record RecipientDataRequest(
        UUID recipientId,
        Optional<Map<String, Object>> context
) {
}
