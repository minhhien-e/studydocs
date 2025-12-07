package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Document(collection = "notification_templates")
@Data
@SuperBuilder
@NoArgsConstructor
public class NotificationTemplateEntity extends MongoEntity {
    @Indexed(unique = true)
    private String name;
    private String channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
