package studydocs.notificationservice.application.dto.input.recipient.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class MarkAsReadInput {
    private UUID recipientId;
    private UUID notificationId;

    public MarkAsReadInput(UUID notificationId) {
        this.notificationId = notificationId;
    }
}
