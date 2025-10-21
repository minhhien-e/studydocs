package studydocs.dto;

import studydocs.domain.Review;
import lombok.Data;

import java.util.UUID;  // Import UUID

@Data
public class ReviewResponse {
    private UUID id;  // UUID object
    private Long documentId;
    private Long userId;
    private Integer rating;
    private String comment;
    private String createdAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.documentId = review.getDocumentId();
        this.userId = review.getUserId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt().toString();
    }
}