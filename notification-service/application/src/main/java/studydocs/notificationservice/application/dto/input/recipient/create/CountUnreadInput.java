package studydocs.notificationservice.application.dto.input.recipient.create;

import lombok.Data;

import java.util.UUID;
@Data
public class CountUnreadInput {
    private UUID recipientId;
    private UUID requesterId;
    public CountUnreadInput(UUID recipientId) {
        this.recipientId = recipientId;
    }
}
