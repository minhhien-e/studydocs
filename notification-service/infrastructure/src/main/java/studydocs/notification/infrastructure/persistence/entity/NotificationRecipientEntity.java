package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "notification_recipients")
@CompoundIndex(def = "{'notificationId': 1, 'recipientId': 1}", unique = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class NotificationRecipientEntity extends MongoEntity {
    private UUID notificationId;
    private UUID recipientId;
    private String renderedSubject;
    private String renderedBody;
    private Boolean isRead;
    private LocalDateTime receivedAt;
    private LocalDateTime deletedAt;
}
