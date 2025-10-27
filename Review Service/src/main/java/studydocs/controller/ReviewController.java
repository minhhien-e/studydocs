package studydocs.controller;

import studydocs.dto.response.ApiResponse;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        ReviewResponse response = reviewService.createReview(req);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable UUID id) {
        ReviewResponse response = reviewService.getReviewById(id);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsByDocumentId(@PathVariable UUID documentId) {
        List<ReviewResponse> responses = reviewService.getReviewsByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success(200, responses));
    }

    @GetMapping("/document/{documentId}/average-rating")
    public ResponseEntity<ApiResponse<Double>> getAverageRating(@PathVariable UUID documentId) {
        Double average = reviewService.getAverageRatingByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success(200, average));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable UUID id,
                                                                    @RequestParam Integer rating,
                                                                    @RequestParam String comment) {
        ReviewResponse response = reviewService.updateReview(id, rating, comment);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Xóa review " + id + " thành công"));
    }
}