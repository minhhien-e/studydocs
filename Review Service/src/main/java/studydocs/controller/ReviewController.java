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
    private final studydocs.service.DocumentReactionService docReactionService;
    private final studydocs.repository.DocumentStatsRepository docStatsRepo;
    private final studydocs.repository.DocumentReactionRepository docReactionRepo;

    @PostMapping
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@Valid @RequestBody CreateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200, commandService.createReview(req)));
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReview(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getReviewById(id)));
    }

    @GetMapping
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.getAllReviews(PageRequest.of(page, size))));
    }

    @GetMapping("/document/{docId}")
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByDocument(
            @PathVariable UUID docId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByDocumentId(docId, PageRequest.of(page, size))));
    }

    @GetMapping("/user/{userId}")
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getByUser(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(200,
                queryService.getReviewsByUserId(userId, PageRequest.of(page, size))));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasAuthority('SCOPE_WRITE_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<ReviewResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateReviewRequest req) {
        return ResponseEntity.ok(ApiResponse.success(200, commandService.updateReview(id, req.getComment())));
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('SCOPE_WRITE_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID id) {
        commandService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Review deleted: " + id));
    }

    @GetMapping("/debug")
    public ResponseEntity<ApiResponse<Object>> debugAuth() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        java.util.Map<String, Object> debugInfo = new java.util.HashMap<>();
        if (auth != null) {
            debugInfo.put("name", auth.getName());
            debugInfo.put("authorities",
                    auth.getAuthorities().stream().map(Object::toString).collect(java.util.stream.Collectors.toList()));
            debugInfo.put("principal", auth.getPrincipal().toString());
            if (auth.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
                debugInfo.put("claims", jwt.getClaims());
            }
        } else {
            debugInfo.put("error", "Authentication is null");
        }
        return ResponseEntity.ok(ApiResponse.success(200, debugInfo));
    }

    @PostMapping("/{id}/react")
    // @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<String>> react(
            @PathVariable UUID id,
            @RequestParam String type) { // "like" hoặc "dislike"

        UUID userId = studydocs.util.SecurityUtils.getCurrentUserId();

        reactionService.react(id, userId, type);
        return ResponseEntity.ok(ApiResponse.success(200, "Reaction updated"));
    }

    @PostMapping("/document/{docId}/react")
    // @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<String>> reactToDocument(
            @PathVariable UUID docId,
            @RequestParam String type) { // "like" or "dislike"

        UUID userId = studydocs.util.SecurityUtils.getCurrentUserId();

        docReactionService.react(docId, userId, type);
        return ResponseEntity.ok(ApiResponse.success(200, "Document reaction updated"));
    }

    @GetMapping("/document/{docId}/stats")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<studydocs.model.DocumentStats>> getDocumentStats(@PathVariable UUID docId) {
        return ResponseEntity.ok(ApiResponse.success(200,
                docStatsRepo.findById(docId).orElse(new studydocs.model.DocumentStats(docId, 0, 0))));
    }

    @GetMapping("/document/{docId}/reaction")
    // @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<studydocs.model.ReviewReaction.ReactionType>> getUserDocumentReaction(
            @PathVariable UUID docId) {

        UUID userId = studydocs.util.SecurityUtils.getCurrentUserId();

        var reaction = docReactionRepo.findByDocumentIdAndUserId(docId, userId).orElse(null);

        return ResponseEntity.ok(ApiResponse.success(200, reaction != null ? reaction.getType() : null));
    }

    @PutMapping("/{id}/hidden")
    // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<String>> toggleHidden(
            @PathVariable UUID id,
            @RequestParam boolean hidden) {

        Review review = reviewRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        review.setHidden(hidden);
        reviewRepo.save(review);

        return ResponseEntity.ok(ApiResponse.success(200, hidden ? "Review hidden" : "Review unhidden"));
    }

    @GetMapping("/user/{userId}/count")
    // @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public ResponseEntity<ApiResponse<Long>> getReviewCountByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(200, queryService.countReviewsByUser(userId)));
    }
}