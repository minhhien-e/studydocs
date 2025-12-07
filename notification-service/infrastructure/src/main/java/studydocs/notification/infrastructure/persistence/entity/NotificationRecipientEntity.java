package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Document(collection = "notification_recipients")
@Data
@SuperBuilder
@NoArgsConstructor
public class NotificationRecipientEntity extends MongoEntity {
    private UUID recipientId;
    private UUID notificationId;
    private Boolean isRead;
    private Map<String,String> personalizedData;
    private LocalDateTime receivedAt;
    private LocalDateTime deletedAt;
}
