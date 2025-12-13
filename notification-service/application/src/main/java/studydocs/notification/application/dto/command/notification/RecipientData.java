package studydocs.notification.application.dto.command.notification;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;

/**
 * Represents complete template data for a single recipient
 * Subject and body data are separated for clarity
 */
@Builder
public record RecipientData(
        UUID recipientId,
        Map<String, String> subjectData,
        Map<String, String> bodyData
) {
}
