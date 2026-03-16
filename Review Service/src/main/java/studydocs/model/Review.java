package studydocs.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {

    @Id
    private UUID id;

    @Indexed
    private UUID documentId;

    private UUID userId;
    private String comment;

    private long likeCount = 0;
    private long dislikeCount = 0;

    private boolean isDeleted = false;
    private boolean isHidden = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime hiddenAt;

    public Review(UUID documentId, UUID userId, String comment) {
        this.id = UUID.randomUUID();
        this.documentId = documentId;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String comment) {
        this.comment = comment;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
        this.hiddenAt = hidden ? LocalDateTime.now() : null;
    }

    public void incrementLike() { this.likeCount++; }
    public void decrementLike() { if (this.likeCount > 0) this.likeCount--; }
    public void incrementDislike() { this.dislikeCount++; }
    public void decrementDislike() { if (this.dislikeCount > 0) this.dislikeCount--; }
}