package studydocs.notificationservice.adapter.input.rest.request.recipient.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAsReadInputCommand;

import java.util.UUID;

@Data
public class MarkAsReadRequest {
    @JsonIgnore
    private UUID recipientId;
    private UUID notificationId;

    public MarkAsReadRequest(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public MarkAsReadInputCommand toInputModel() {
        return new MarkAsReadInputCommand(recipientId, notificationId);
    }
}
