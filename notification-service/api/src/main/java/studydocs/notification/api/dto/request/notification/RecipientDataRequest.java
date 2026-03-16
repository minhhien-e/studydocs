package studydocs.notification.api.dto.request.notification;

import java.util.Map;
import java.util.UUID;

public record RecipientDataRequest(
        UUID recipientId,
       Map<String, Object> context
) {
}
