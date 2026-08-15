package com.studydocs.modules.review.controller;

import com.studydocs.modules.review.dto.ReviewDto;
import com.studydocs.modules.review.service.ReviewService;
import com.studydocs.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/document")
    public ApiResponse<ReviewDto.ReviewResponse> addOrUpdateReview(
            Authentication authentication,
            @Valid @RequestBody ReviewDto.CreateReviewRequest request) {
        String userId = authentication.getName();
        return ApiResponse.success(reviewService.addOrUpdateReview(userId, request));
    }

    @GetMapping("/document")
    public ApiResponse<List<ReviewDto.ReviewResponse>> getDocumentReviews(@RequestParam("documentId") String documentId) {
        return ApiResponse.success(reviewService.getDocumentReviews(documentId));
    }

    @GetMapping("/user/me/reactions/count")
    public ApiResponse<Map<String, Long>> getMyReactionCount(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(reviewService.getUserReactionCount(userId));
    }

    @GetMapping("/user/{userId}/count")
    public ApiResponse<Map<String, Long>> getUserReviewCount(@PathVariable String userId) {
        return ApiResponse.success(reviewService.getUserReviewCount(userId));
    }

    @GetMapping("/admin/stats/reviews/total")
    public ApiResponse<Map<String, Long>> getTotalReviews() {
        return ApiResponse.success(reviewService.getUserReviewCount(null));
    }

    @GetMapping("/admin/stats/reactions/total-likes")
    public ApiResponse<Map<String, Long>> getTotalDocumentLikes() {
        return ApiResponse.success(reviewService.getUserReactionCount(null));
    }
}
