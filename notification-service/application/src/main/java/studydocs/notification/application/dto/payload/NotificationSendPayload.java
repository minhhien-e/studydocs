package studydocs.notification.application.dto.payload;

import java.util.List;

public record NotificationSendPayload(String subject, String body, List<String> destinations){
}
