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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse getReviewById(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(600, id));  // REVIEW_NOT_FOUND
        return new ReviewResponse(review);
    }

    public Page<ReviewResponse> getReviewsByDocumentId(UUID documentId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByDocumentIdAndIsDeletedFalse(documentId, pageable);
        return reviews.map(ReviewResponse::new);
    }

    public Double getAverageRatingByDocumentId(UUID documentId) {
        return reviewRepository.findAverageRatingByDocumentId(documentId);
    }
}