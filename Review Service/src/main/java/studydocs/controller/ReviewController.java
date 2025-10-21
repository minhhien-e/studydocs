package studydocs.controller;

import studydocs.dto.ApiResponse;
import studydocs.dto.CreateReviewRequest;
import studydocs.dto.ReviewResponse;
import studydocs.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;  // Import UUID

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        ReviewResponse response = reviewService.createReview(req);
        return ResponseEntity.ok(ApiResponse.success("Tạo review thành công", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable UUID id) {  // UUID thay String
        ReviewResponse response = reviewService.getReviewById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy review thành công", response));
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsByDocumentId(@PathVariable Long documentId) {
        List<ReviewResponse> responses = reviewService.getReviewsByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách review thành công", responses));
    }

    @GetMapping("/document/{documentId}/average-rating")
    public ResponseEntity<ApiResponse<Double>> getAverageRating(@PathVariable Long documentId) {
        Double average = reviewService.getAverageRatingByDocumentId(documentId);
        return ResponseEntity.ok(ApiResponse.success("Lấy điểm trung bình thành công", average));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable UUID id,  // UUID thay String
                                                                    @RequestParam Integer rating,
                                                                    @RequestParam String comment) {
        ReviewResponse response = reviewService.updateReview(id, rating, comment);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật review thành công", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID id) {  // UUID thay String
        reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa review " + id + " thành công", null));
    }
}