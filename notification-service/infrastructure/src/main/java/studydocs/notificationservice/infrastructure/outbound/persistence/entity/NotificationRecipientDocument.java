package studydocs.notificationservice.infrastructure.outbound.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "notification_recipient")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRecipientDocument {
    @MongoId
    private UUID id;
    private UUID recipientId;
    private UUID notificationId;
    private boolean isRead;
    private LocalDateTime deletedAt;
    private NotificationDocument notification;
}
