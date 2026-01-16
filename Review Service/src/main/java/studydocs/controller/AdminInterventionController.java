package studydocs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studydocs.dto.response.ApiResponse;
import studydocs.service.AdminInterventionService;

import java.util.UUID;

@RestController
@PreAuthorize("hasAuthority('SCOPE_READ_USER')")
@RequestMapping("/api/v1/reviews/admin")
@RequiredArgsConstructor
public class AdminInterventionController {

    private final AdminInterventionService adminInterventionService;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUserReviews(@PathVariable UUID userId) {
        adminInterventionService.deleteUserReviews(userId);
        return ResponseEntity.ok(ApiResponse.success(200, "All reviews for user " + userId + " have been deleted."));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable UUID reviewId) {
        adminInterventionService.deleteReview(reviewId);
        return ResponseEntity.ok(ApiResponse.success(200, "Review " + reviewId + " has been deleted."));
    }
}
