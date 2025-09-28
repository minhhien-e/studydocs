package studydocs.notificationservice.application.dto.input.recipient.read;

import lombok.Data;

import java.util.UUID;

@Data
public class CountUnreadInput {
    private UUID recipientId;

    public CountUnreadInput(UUID recipientId) {
        this.recipientId = recipientId;
    }
}
