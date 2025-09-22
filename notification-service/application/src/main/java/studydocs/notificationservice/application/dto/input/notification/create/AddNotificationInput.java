package studydocs.notificationservice.application.dto.input.notification.create;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class AddNotificationInput {
    private UUID senderId;
    private UUID templateId;
    private String chanel;
    private String category;
    private Map<String, Object> templateData;
    private UUID recipientId;
}