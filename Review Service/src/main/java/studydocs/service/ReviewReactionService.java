package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.model.ReviewReaction;
import studydocs.repository.ReviewReactionRepository;
import studydocs.repository.ReviewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewReactionService {

    private final ReviewReactionRepository reactionRepo;
    private final ReviewRepository reviewRepo;

    @Transactional
    public void react(UUID reviewId, UUID userId, String typeStr) {
        Review review = reviewRepo.findByIdAndIsDeletedFalseAndIsHiddenFalse(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        ReviewReaction.ReactionType type;
        try {
            type = ReviewReaction.ReactionType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type must be LIKE or DISLIKE");
        }

        ReviewReaction existing = reactionRepo.findByReviewIdAndUserId(reviewId, userId).orElse(null);

        if (existing == null) {
            // Thêm mới
            reactionRepo.save(new ReviewReaction(reviewId, userId, type));
            if (type == ReviewReaction.ReactionType.LIKE) review.incrementLike();
            else review.incrementDislike();

        } else if (existing.getType() == type) {
            // Bỏ react
            reactionRepo.delete(existing);
            if (type == ReviewReaction.ReactionType.LIKE) review.decrementLike();
            else review.decrementDislike();

        } else {
            // Chuyển từ like ↔ dislike
            existing.setType(type);
            reactionRepo.save(existing);
            if (type == ReviewReaction.ReactionType.LIKE) {
                review.incrementLike();
                review.decrementDislike();
            } else {
                review.incrementDislike();
                review.decrementLike();
            }
        }

        reviewRepo.save(review);
    }
}