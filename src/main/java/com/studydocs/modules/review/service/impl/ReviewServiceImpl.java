package com.studydocs.modules.review.service.impl;

import com.studydocs.modules.review.dto.ReviewDto;
import com.studydocs.modules.review.entity.DocumentReviewEntity;
import com.studydocs.modules.review.repository.ReviewRepository;
import com.studydocs.modules.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewDto.ReviewResponse addOrUpdateReview(String userId, ReviewDto.CreateReviewRequest request) {
        DocumentReviewEntity entity = reviewRepository.findByDocumentIdAndUserId(request.getDocumentId(), userId)
                .orElseGet(() -> DocumentReviewEntity.builder()
                        .documentId(request.getDocumentId())
                        .userId(userId)
                        .build());

        if (request.getRating() != null) entity.setRating(request.getRating());
        if (request.getComment() != null) entity.setComment(request.getComment());
        if (request.getReactionType() != null) entity.setReactionType(request.getReactionType());

        DocumentReviewEntity saved = reviewRepository.save(entity);
        return toReviewResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto.ReviewResponse> getDocumentReviews(String documentId) {
        return reviewRepository.findByDocumentId(documentId).stream()
                .map(this::toReviewResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getUserReactionCount(String userId) {
        long count = (userId != null) ? reviewRepository.countByUserIdAndReactionTypeNotNull(userId) : reviewRepository.count();
        return Map.of("count", count);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getUserReviewCount(String userId) {
        long count = (userId != null) ? reviewRepository.countByUserId(userId) : reviewRepository.count();
        return Map.of("count", count);
    }

    private ReviewDto.ReviewResponse toReviewResponse(DocumentReviewEntity entity) {
        return ReviewDto.ReviewResponse.builder()
                .id(entity.getId())
                .documentId(entity.getDocumentId())
                .userId(entity.getUserId())
                .rating(entity.getRating())
                .comment(entity.getComment())
                .reactionType(entity.getReactionType())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)
                .build();
    }
}
