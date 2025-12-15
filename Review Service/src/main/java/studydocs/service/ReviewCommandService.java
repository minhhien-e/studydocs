package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.client.DocumentClient;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.repository.ReviewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final DocumentClient documentClient;

    public ReviewResponse createReview(CreateReviewRequest req) {
        documentClient.validateDocumentId(req.getDocumentId());

        Review review = new Review(
                req.getDocumentId(),
                req.getUserId(),
                req.getComment()
        );

        reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(UUID id, String comment) {
        Review review = reviewRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        review.update(comment);
        reviewRepository.save(review);

        return new ReviewResponse(review);
    }

    public void deleteReview(UUID id) {
        Review review = reviewRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        review.markAsDeleted();
        reviewRepository.save(review);
    }
}
