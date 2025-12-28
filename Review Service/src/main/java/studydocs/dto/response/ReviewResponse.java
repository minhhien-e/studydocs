package studydocs.dto.response;

import lombok.Setter;
import studydocs.model.Review;
import lombok.Data;

import java.util.UUID;

@Data
public class ReviewResponse {

    private UUID id;
    private UUID documentId;
    private UUID userId;
    private String comment;
    private long likeCount;
    private long dislikeCount;
    private String createdAt;

    // Thêm field để FE biết user hiện tại đã like/dislike chưa (nếu cần)
    @Setter
    private String currentUserReaction; // null, "LIKE", "DISLIKE"

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.documentId = review.getDocumentId();
        this.userId = review.getUserId();
        this.comment = review.getComment();
        this.likeCount = review.getLikeCount();
        this.dislikeCount = review.getDislikeCount();
        this.createdAt = review.getCreatedAt().toString();
    }

}