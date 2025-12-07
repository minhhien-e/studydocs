package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "notifications")
@Data
@SuperBuilder
@NoArgsConstructor
public class NotificationEntity extends MongoEntity {
    private UUID templateId;
    private UUID senderId;
    private String channel;
    private String category;
    private Map<String, String> templateData;
    @CreatedDate
    private LocalDateTime createdAt;
    private List<NotificationRecipientEntity> notificationRecipients;
    private NotificationTemplateEntity notificationTemplate;
}
