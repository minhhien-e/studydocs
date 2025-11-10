package studydocs.service;

import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.repository.ReviewRepository;
import studydocs.client.DocumentClient;  // Thay vì DocumentValidator
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final DocumentClient documentClient;  // Updated

    public ReviewResponse createReview(CreateReviewRequest req) {
        documentClient.validateDocumentId(req.getDocumentId());  // Gọi client

        Review review = new Review(req.getDocumentId(), req.getUserId(), req.getRating(), req.getComment());
        review = reviewRepository.save(review);

        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(UUID id, Integer rating, String comment) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(600, id));
        review.update(rating, comment);
        review = reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    public void deleteReview(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(600, id));
        review.markAsDeleted();
        reviewRepository.save(review);
    }
}