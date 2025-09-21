package studydocs.notificationservice.application.dto.input.notification.create;

import lombok.Builder;
import lombok.Getter;
import studydocs.notificationservice.domain.model.entity.Notification;

import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class AddNotificationInput {
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
