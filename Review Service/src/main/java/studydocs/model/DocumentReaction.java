package studydocs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import studydocs.model.ReviewReaction.ReactionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "document_reactions")
public class DocumentReaction {

    @Id
    private String id; // documentId_userId

    private UUID documentId;
    private UUID userId;
    private ReactionType type;

    private LocalDateTime createdAt;

    public DocumentReaction(UUID documentId, UUID userId, ReactionType type) {
        this.id = documentId + "_" + userId;
        this.documentId = documentId;
        this.userId = userId;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }
}
