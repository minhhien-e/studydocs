package studydocs.service;

import studydocs.domain.Review;
import studydocs.dto.CreateReviewRequest;
import studydocs.dto.ReviewResponse;
import studydocs.exception.DocumentValidationException;
import studydocs.exception.ReviewNotFoundException;
import studydocs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;  // Import UUID
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestTemplate restTemplate;

    @Value("${document.service.url}")
    private String documentServiceUrl;

    public ReviewResponse createReview(CreateReviewRequest req) {
        // Kiểm tra document tồn tại bằng cách gọi Document Service
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(documentServiceUrl + "/" + req.getDocumentId(), String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new DocumentValidationException("Document không tồn tại hoặc không hợp lệ");
            }
        } catch (Exception ex) {
            throw new DocumentValidationException("Lỗi khi kiểm tra document: " + ex.getMessage());
        }

        // Tạo review mới
        Review review = new Review(req.getDocumentId(), req.getUserId(), req.getRating(), req.getComment());
        review = reviewRepository.save(review);

        System.out.println("ReviewService: Tạo review id=" + review.getId() + " cho documentId=" + req.getDocumentId());

        return new ReviewResponse(review);
    }

    public ReviewResponse getReviewById(UUID id) {  // UUID thay String
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getReviewsByDocumentId(Long documentId) {
        List<Review> reviews = reviewRepository.findByDocumentIdAndIsDeletedFalse(documentId);
        return reviews.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    public Double getAverageRatingByDocumentId(Long documentId) {
        return reviewRepository.findAverageRatingByDocumentId(documentId);
    }

    public ReviewResponse updateReview(UUID id, Integer rating, String comment) {  // UUID thay String
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        review.update(rating, comment);
        review = reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    public void deleteReview(UUID id) {  // UUID thay String
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        review.markAsDeleted();
        reviewRepository.save(review);
    }
}