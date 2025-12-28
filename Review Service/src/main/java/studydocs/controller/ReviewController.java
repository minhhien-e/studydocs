package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import studydocs.dto.request.CreateReviewRequest;
import studydocs.dto.request.UpdateReviewRequest;
import studydocs.dto.response.ApiResponse;
import studydocs.dto.response.ReviewResponse;
import studydocs.exception.ReviewNotFoundException;
import studydocs.model.Review;
import studydocs.service.ReviewCommandService;
import studydocs.service.ReviewQueryService;
import studydocs.service.ReviewReactionService;
import studydocs.repository.ReviewRepository;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService commandService;
    private final ReviewQueryService queryService;
    private final ReviewReactionService reactionService;
    private final ReviewRepository reviewRepo;

    @PostMapping
    @PreAuthorize("hasRole('write')")
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200, commandService.createReview(req)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('read')")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReview(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getReviewById(id)));
    }

    @GetMapping
    @PreAuthorize("hasRole('read')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getAllReviews(PageRequest.of(page, size))));
    }

    @GetMapping("/document/{docId}")
    @PreAuthorize("hasRole('read')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByDocument(
            @PathVariable UUID docId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByDocumentId(docId, PageRequest.of(page, size))));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('read')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByUser(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByUserId(userId, PageRequest.of(page, size))));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('write')")
    public ResponseEntity<ApiResponse<ReviewResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200, commandService.updateReview(id, req.getComment())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('delete')")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable UUID id) {
        commandService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Deleted review: " + id));
    }

    @PostMapping("/{id}/react")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<String>> react(
            @PathVariable UUID id,
            @RequestParam String type) { // "like" hoặc "dislike"

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdStr;

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            userIdStr = jwt.getSubject();
        } else {
            userIdStr = authentication.getName();
        }

        UUID userId = UUID.fromString(userIdStr);

        reactionService.react(id, userId, type);
        return ResponseEntity.ok(ApiResponse.success(200, "Reaction updated"));
    }

    @PutMapping("/{id}/hidden")
    @PreAuthorize("hasRole('admin') or hasRole('moderate')")
    public ResponseEntity<ApiResponse<String>> toggleHidden(
            @PathVariable UUID id,
            @RequestParam boolean hidden) {

        Review review = reviewRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        review.setHidden(hidden);
        reviewRepo.save(review);

        return ResponseEntity.ok(ApiResponse.success(200, hidden ? "Review hidden" : "Review unhidden"));
    }
}