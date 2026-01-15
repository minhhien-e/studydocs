package studydocs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "document_stats")
public class DocumentStats {

    @Id
    private UUID documentId;

    private long likeCount;
    private long dislikeCount;

    public void incrementLike() {
        this.likeCount++;
    }

    public void decrementLike() {
        if (this.likeCount > 0)
            this.likeCount--;
    }

    public void incrementDislike() {
        this.dislikeCount++;
    }

    public void decrementDislike() {
        if (this.dislikeCount > 0)
            this.dislikeCount--;
    }
}
