package studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MarkAsReadRequest {
    private UUID notificationId;

    public MarkAsReadInput toInput() {
        return new MarkAsReadInput(notificationId);
    }
}
