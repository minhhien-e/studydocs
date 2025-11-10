package studydocs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.request.UpdateReviewRequest;  // Mới
import studydocs.dto.response.ReviewResponse;
import studydocs.service.ReviewCommandService;
import studydocs.service.ReviewQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PreAuthorize("hasRole('write')")
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        ReviewResponse response = reviewCommandService.createReview(req);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @PreAuthorize("hasRole('read')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable UUID id) {
        ReviewResponse response = reviewQueryService.getReviewById(id);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    // Lấy tất cả review
    @PreAuthorize("hasRole('read')")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getAllReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> responses = reviewQueryService.getAllReviews(pageable);
        return ResponseEntity.ok(ApiResponse.success(200, responses));
    }

    // Lấy review theo documentId
    @PreAuthorize("hasRole('read')")
    @GetMapping("/document/{documentId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getReviewsByDocumentId(
            @PathVariable UUID documentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer rating) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> responses;

        if (rating != null) {
            responses = reviewQueryService.getReviewsByDocumentIdAndRating(documentId, rating, pageable);
        } else {
            responses = reviewQueryService.getReviewsByDocumentId(documentId, pageable);
        }

        return ResponseEntity.ok(ApiResponse.success(200, responses));
    }

    // Lấy review theo userId
    @PreAuthorize("hasRole('read')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getReviewsByUserId(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> responses = reviewQueryService.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(ApiResponse.success(200, responses));
    }

    @GetMapping("/document/{documentId}/average-rating")
    public ResponseEntity<ApiResponse<Double>> getAverageRating(@PathVariable UUID documentId) {
        Double average = reviewQueryService.getAverageRatingByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success(200, average));
    }

    @PreAuthorize("hasRole('write')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateReviewRequest req) {  // Đổi thành @RequestBody

        ReviewResponse response = reviewCommandService.updateReview(id, req.getRating(), req.getComment());
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @PreAuthorize("hasRole('delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID id) {
        reviewCommandService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Xóa review " + id + " thành công"));
    }
}