package studydocs.notificationservice.infrastructure.inbound.web.request.recipient.read;

import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;

import java.util.UUID;

public record CountUnreadRequest(UUID recipientId) {
    public CountUnreadInput toInput() {
        return new CountUnreadInput(recipientId);
    }
}
