package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * MongoDB entity for user notification profiles.
 * Stores notification delivery preferences and endpoints.
 */
@EqualsAndHashCode(callSuper = true)
@Document(collection = "user_notification_profiles")
@Data
@SuperBuilder
@NoArgsConstructor
public class UserNotificationProfileEntity extends MongoEntity {
    @Indexed(unique = true)
    private UUID userId;
    
    private List<String> fcmTokens;
    private String emailAddress;
    private String phoneNumber;
    
    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;
}
