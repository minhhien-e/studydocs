package studydocs.notificationservice.adapter.input.rest.request.recipient.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAllAsReadInputModel;

import java.util.UUID;

@Data
public class MarkAllAsReadRequest {
    @JsonIgnore
    private UUID recipientId;

    public MarkAllAsReadInputModel toInputModel() {
        return new MarkAllAsReadInputModel(recipientId);
    }
}
