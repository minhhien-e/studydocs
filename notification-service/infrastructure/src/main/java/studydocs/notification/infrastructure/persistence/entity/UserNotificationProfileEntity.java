package studydocs.notification.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
@Data
@SuperBuilder
@NoArgsConstructor
public class UserEntity extends MongoEntity {
    private List<String> fcmTokens;
    private String email;
    private String phoneNumber;
}
