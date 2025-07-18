package studydocs.notificationservice.infrastructure.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDocument {
    @MongoId
    private UUID id;
    private UUID templateId;
    private UUID senderId;
    private String chanel;
    private Map<String, Object> templateData;
    private LocalDateTime createdAt;
}
