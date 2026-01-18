package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    private final studydocs.service.NotificationService notificationService;

    // Method helper lấy userId từ JWT
    private UUID getCurrentUserId() {
        return studydocs.util.SecurityUtils.getCurrentUserId();
    }

    public ReviewResponse createReview(CreateReviewRequest req) {
        documentClient.validateDocumentId(req.getDocumentId());

        UUID userId = getCurrentUserId();
        if (userId == null) {
            throw new RuntimeException("Không thể xác định người dùng từ token. Vui lòng đăng nhập lại.");
            // Hoặc tạo exception riêng sau nếu cần
        }

        Review review = new Review(
                req.getDocumentId(),
                userId, // <-- lấy từ JWT
                req.getComment());

        reviewRepository.save(review);

        // Send Notification
        notificationService.sendReviewCreated(new studydocs.config.dto.ReviewCreatedPayload(
                review.getId(),
                req.getDocumentId(),
                userId,
                req.getComment()));

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