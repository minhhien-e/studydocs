package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.repository.ReviewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository repo;

    public ReviewResponse getReviewById(UUID id) {
        Review review = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        return new ReviewResponse(review);
    }

    public Page<ReviewResponse> getAllReviews(Pageable pageable) {
        return repo.findByIsDeletedFalse(pageable)
                .map(ReviewResponse::new);
    }

    public Page<ReviewResponse> getReviewsByDocumentId(UUID docId, Pageable pageable) {
        return repo.findByDocumentIdAndIsDeletedFalse(docId, pageable)
                .map(ReviewResponse::new);
    }

    public Page<ReviewResponse> getReviewsByUserId(UUID userId, Pageable pageable) {
        return repo.findByUserIdAndIsDeletedFalse(userId, pageable)
                .map(ReviewResponse::new);
    }
}
