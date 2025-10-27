package studydocs.service;

import studydocs.model.Review;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.DocumentValidationException;
import studydocs.exception.ReviewNotFoundException;
import studydocs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //tự động inject
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestTemplate restTemplate;  // Tách bean

    @Value("${document.service.url:}")
    private String documentServiceUrl;

    public ReviewResponse createReview(CreateReviewRequest req) {
        try {
            String docUrl = documentServiceUrl + "/" + req.getDocumentId();
            ResponseEntity<String> response = restTemplate.getForEntity(docUrl, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new DocumentValidationException(602);  // DOCUMENT_INVALID
            }
        } catch (Exception ex) {
            throw new DocumentValidationException(603);  // SYSTEM_ERROR
        }

        Review review = new Review(req.getDocumentId(), req.getUserId(), req.getRating(), req.getComment());
        review = reviewRepository.save(review);

        System.out.println("ReviewService: Tạo review id=" + review.getId());

        return new ReviewResponse(review);
    }

    public ReviewResponse getReviewById(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(600, id));  // REVIEW_NOT_FOUND
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getReviewsByDocumentId(UUID documentId) {
        List<Review> reviews = reviewRepository.findByDocumentIdAndIsDeletedFalse(documentId);
        return reviews.stream().map(ReviewResponse::new).collect(Collectors.toList());
    }

    public Double getAverageRatingByDocumentId(UUID documentId) {
        return reviewRepository.findAverageRatingByDocumentId(documentId);
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