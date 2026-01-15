package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.AggregateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "user_notification_profiles")
@Data
@SuperBuilder
@NoArgsConstructor
public class UserNotificationProfileEntity extends AggregateEntity {
    @Indexed(unique = true)
    private UUID userId;
    
    private String emailAddress;
    private String phoneNumber;
    
    private boolean pushEnabled;
    private boolean emailEnabled;
    private boolean smsEnabled;
}
