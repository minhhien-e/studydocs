package studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MarkAsReadRequest {
    private UUID notificationId;
}
