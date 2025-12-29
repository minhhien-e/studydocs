package studydocs.notification.api.dto.request.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class ReceiveNotificationRequest {
    @JsonIgnore
    private UUID notificationId;
    private Map<String, Object> context;
}
