package studydocs.notificationservice.infrastructure.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDocument {
    @MongoId
    private UUID id;
    private UUID recipientId;
    private UUID notificationId;
    private boolean isRead;
    private boolean isDeleted;
}
