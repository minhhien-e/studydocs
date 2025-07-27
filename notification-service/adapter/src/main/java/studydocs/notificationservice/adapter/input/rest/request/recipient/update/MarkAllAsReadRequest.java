package studydocs.notificationservice.adapter.input.rest.request.recipient.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAllAsReadInputCommand;

import java.util.UUID;

@Data
public class MarkAllAsReadRequest {
    @JsonIgnore
    private UUID recipientId;

    public MarkAllAsReadInputCommand toInputModel() {
        return new MarkAllAsReadInputCommand(recipientId);
    }
}
