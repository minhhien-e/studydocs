package studydocs.notificationservice.infrastructure.outbound.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDocument {
    @MongoId
    private UUID id;
    private UUID templateId;
    private UUID senderId;
    private String type;
    private Map<String, Object> templateData;
    @CreatedDate
    private LocalDateTime createdAt;
}
