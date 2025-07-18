package studydocs.notificationservice.domain.event;


import java.util.Map;
import java.util.UUID;

public record SendMailEvent(UUID userId, String email, Map<String, Object> templateData, String templateName) {

}
