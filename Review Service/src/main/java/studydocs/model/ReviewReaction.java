package studydocs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "review_reactions")
public class ReviewReaction {

    @Id
    private String id; // reviewId_userId

    private UUID reviewId;
    private UUID userId;
    private ReactionType type;

    private LocalDateTime createdAt;

    public enum ReactionType {
        LIKE, DISLIKE
    }

    public ReviewReaction(UUID reviewId, UUID userId, ReactionType type) {
        this.id = reviewId + "_" + userId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }
}