package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.request.UpdateReviewRequest;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.ReviewResponse;
import studydocs.service.ReviewCommandService;
import studydocs.service.ReviewQueryService;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService commandService;
    private final ReviewQueryService queryService;

    @PreAuthorize("hasRole('write')")
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200, commandService.createReview(req)));
    }

    @PreAuthorize("hasRole('read')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReview(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getReviewById(id)));
    }

    @PreAuthorize("hasRole('read')")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getAllReviews(PageRequest.of(page, size))));
    }

    @PreAuthorize("hasRole('read')")
    @GetMapping("/document/{docId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByDocument(
            @PathVariable UUID docId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByDocumentId(docId, PageRequest.of(page, size))));
    }

    @PreAuthorize("hasRole('read')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByUser(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByUserId(userId, PageRequest.of(page, size))));
    }

    @PreAuthorize("hasRole('write')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200,
                commandService.updateReview(id, req.getComment())));
    }

    @PreAuthorize("hasRole('delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        commandService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Deleted review: " + id));
    }
}
