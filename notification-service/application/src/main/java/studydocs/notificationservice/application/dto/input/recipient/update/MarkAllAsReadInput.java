package studydocs.notificationservice.application.dto.input.recipient.update;

import lombok.Data;

import java.util.UUID;
@Data
public class MarkAllAsReadInput{
    private UUID recipientId;
}
