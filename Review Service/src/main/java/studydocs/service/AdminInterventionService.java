package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.model.Review;
import studydocs.repository.ReviewRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminInterventionService {

    private final ReviewRepository reviewRepository;

    public void deleteUserReviews(UUID userId) {
        List<Review> reviews = reviewRepository.findAllByUserIdAndIsDeletedFalse(userId);
        for (Review review : reviews) {
            review.markAsDeleted();
        }
        reviewRepository.saveAll(reviews);
    }

    public void deleteReview(UUID reviewId) {
        reviewRepository.findByIdAndIsDeletedFalse(reviewId).ifPresent(review -> {
            review.markAsDeleted();
            reviewRepository.save(review);
        });
    }
}
