package studydocs.notification.api.dto.request.notification;

import java.util.Map;
import java.util.UUID;

/**
 * Recipient data for API layer
 * Frontend will send data already merged (common + personal)
 */
public record RecipientDataRequest(
        UUID recipientId,
        Map<String, String> subjectData,
        Map<String, String> bodyData
) {
}
