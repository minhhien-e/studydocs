package studydocs.dto.response;

import studydocs.model.Review;
import lombok.Data;

import java.util.UUID;

@Data
public class ReviewResponse {

    private UUID id;
    private UUID documentId;
    private UUID userId;
    private String comment;
    private String createdAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.documentId = review.getDocumentId();
        this.userId = review.getUserId();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt().toString();
    }
}