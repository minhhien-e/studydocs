package studydocs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse getReviewById(UUID id) {
        Review review = reviewRepository.findByIdAndIsDeletedFalse(id)  // Filter deleted
                .orElseThrow(() -> new ReviewNotFoundException(600, id));
        return new ReviewResponse(review);
    }

    public Page<ReviewResponse> getAllReviews(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByIsDeletedFalse(pageable);
        return reviews.map(ReviewResponse::new);
    }

    public Page<ReviewResponse> getReviewsByDocumentId(UUID documentId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByDocumentIdAndIsDeletedFalse(documentId, pageable);
        return reviews.map(ReviewResponse::new);
    }

    public Page<ReviewResponse> getReviewsByDocumentIdAndRating(UUID documentId, Integer rating, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByDocumentIdAndRatingAndIsDeletedFalse(documentId, rating, pageable);
        return reviews.map(ReviewResponse::new);
    }

    public Page<ReviewResponse> getReviewsByUserId(UUID userId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByUserIdAndIsDeletedFalse(userId, pageable);
        return reviews.map(ReviewResponse::new);
    }

    public Double getAverageRatingByDocumentId(UUID documentId) {
        return reviewRepository.findAverageRatingByDocumentId(documentId);
    }
}