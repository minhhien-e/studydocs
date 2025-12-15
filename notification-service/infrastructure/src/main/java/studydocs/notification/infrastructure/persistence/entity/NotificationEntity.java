package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private String type;
    private String snapshotSubject;
    private String snapshotBody;
    @CreatedDate
    private LocalDateTime createdAt;
}
