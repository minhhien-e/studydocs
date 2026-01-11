package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.model.ReviewReaction;
import studydocs.repository.ReviewReactionRepository;
import studydocs.repository.ReviewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepo;
    private final ReviewReactionRepository reactionRepo;

    public ReviewResponse getReviewById(UUID id) {
        Review review = reviewRepo.findByIdAndIsDeletedFalseAndIsHiddenFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        ReviewResponse response = new ReviewResponse(review);
        UUID currentUserId = getCurrentUserId();
        if (currentUserId != null) {
            reactionRepo.findByReviewIdAndUserId(id, currentUserId)
                    .ifPresent(r -> response.setCurrentUserReaction(r.getType().name()));
        }
        return response;
    }

    public Page<ReviewResponse> getAllReviews(Pageable pageable) {
        return reviewRepo.findByIsDeletedFalseAndIsHiddenFalse(pageable)
                .map(this::toResponseWithReaction);
    }

    public Page<ReviewResponse> getReviewsByDocumentId(UUID docId, Pageable pageable) {
        return reviewRepo.findByDocumentIdAndIsDeletedFalseAndIsHiddenFalse(docId, pageable)
                .map(this::toResponseWithReaction);
    }

    public Page<ReviewResponse> getReviewsByUserId(UUID userId, Pageable pageable) {
        return reviewRepo.findByUserIdAndIsDeletedFalseAndIsHiddenFalse(userId, pageable)
                .map(this::toResponseWithReaction);
    }

    public long countReviewsByUser(UUID userId) {
        return reviewRepo.countByUserIdAndIsDeletedFalseAndIsHiddenFalse(userId);
    }

    private ReviewResponse toResponseWithReaction(Review review) {
        ReviewResponse resp = new ReviewResponse(review);
        UUID currentUserId = getCurrentUserId();
        if (currentUserId != null) {
            reactionRepo.findByReviewIdAndUserId(review.getId(), currentUserId)
                    .ifPresent(r -> resp.setCurrentUserReaction(r.getType().name()));
        }
        return resp;
    }

    private UUID getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }
        // Giả sử userId nằm trong claim "sub" hoặc "userId" của JWT
        String userIdStr = auth.getName(); // thường là sub (UUID string)
        try {
            return UUID.fromString(userIdStr);
        } catch (Exception e) {
            return null;
        }
    }
}