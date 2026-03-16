package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.AggregateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "fcm_tokens")
@Data
@SuperBuilder
@NoArgsConstructor
public class FcmTokenEntity extends AggregateEntity {
    private String value;
    private UUID userId;
}
