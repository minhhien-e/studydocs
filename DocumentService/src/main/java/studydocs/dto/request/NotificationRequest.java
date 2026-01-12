package studydocs.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private UUID userId;
    private UUID senderId;
    private String subject;
    private String body;
    private String type;

    @JsonProperty("isRead")
    private boolean isRead;
}
