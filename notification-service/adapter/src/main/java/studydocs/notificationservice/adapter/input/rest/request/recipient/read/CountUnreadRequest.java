package studydocs.notificationservice.adapter.input.rest.request.recipient.read;

import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create.CountUnreadInputModel;

import java.util.UUID;

public record CountUnreadRequest(UUID recipientId) {
    public CountUnreadInputModel toInputModel() {
        return new CountUnreadInputModel(recipientId);
    }
}
