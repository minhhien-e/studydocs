package studydocs.notificationservice.infrastructure.inbound.web.request.recipient.update;

import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
public class MarkAllAsReadRequest {
    public MarkAllAsReadInput toInput() {
        return new MarkAllAsReadInput();
    }
}
