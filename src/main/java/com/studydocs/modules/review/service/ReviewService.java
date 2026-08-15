package com.studydocs.modules.review.service;

import com.studydocs.modules.review.dto.ReviewDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    ReviewDto.ReviewResponse addOrUpdateReview(String userId, ReviewDto.CreateReviewRequest request);
    List<ReviewDto.ReviewResponse> getDocumentReviews(String documentId);
    Map<String, Long> getUserReactionCount(String userId);
    Map<String, Long> getUserReviewCount(String userId);
}
