package studydocs.notificationservice.infrastructure.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplateDocument {
    @MongoId
    private UUID id;
    @Indexed(unique = true)
    private String name;
    private String channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
