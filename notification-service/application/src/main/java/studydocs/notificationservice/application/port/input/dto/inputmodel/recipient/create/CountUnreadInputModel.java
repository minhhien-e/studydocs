package studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create;

import lombok.Data;

import java.util.UUID;
@Data
public class CountUnreadInputModel {
    private UUID recipientId;
    private UUID requesterId;
    public CountUnreadInputModel(UUID recipientId) {
        this.recipientId = recipientId;
    }
}
