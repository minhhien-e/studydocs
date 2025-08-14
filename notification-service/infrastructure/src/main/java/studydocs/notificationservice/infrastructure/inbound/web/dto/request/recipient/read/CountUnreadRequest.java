package studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read;

import java.util.UUID;

public record CountUnreadRequest(UUID recipientId) {
}
