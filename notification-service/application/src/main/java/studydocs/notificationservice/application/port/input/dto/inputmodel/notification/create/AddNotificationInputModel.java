package studydocs.notificationservice.application.port.input.dto.inputmodel.notification.create;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.domain.entities.Notification;

import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class AddNotificationInputModel {
    private UUID senderId;
    private UUID templateId;
    private String chanel;
    private String type;
    private Map<String, Object> templateData;
    private UUID recipientId;

    public Notification toDomain() {
        return new Notification(
                templateId,
                senderId,
                type,
                templateData);
    }
}
