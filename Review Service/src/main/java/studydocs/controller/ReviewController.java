package studydocs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.response.ReviewResponse;
import studydocs.service.ReviewCommandService;
import studydocs.service.ReviewQueryService;
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

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        ReviewResponse response = reviewCommandService.createReview(req);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable UUID id) {
        ReviewResponse response = reviewQueryService.getReviewById(id);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getReviewsByDocumentId(@PathVariable UUID documentId,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> responses = reviewQueryService.getReviewsByDocumentId(documentId, pageable);
        return ResponseEntity.ok(ApiResponse.success(200, responses));
    }

    @GetMapping("/document/{documentId}/average-rating")
    public ResponseEntity<ApiResponse<Double>> getAverageRating(@PathVariable UUID documentId) {
        Double average = reviewQueryService.getAverageRatingByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success(200, average));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable UUID id,
                                                                    @RequestParam Integer rating,
                                                                    @RequestParam String comment) {
        ReviewResponse response = reviewCommandService.updateReview(id, rating, comment);
        return ResponseEntity.ok(ApiResponse.success(200, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID id) {
        reviewCommandService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Xóa review " + id + " thành công"));
    }
}