package com.studydocs.modules.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReviewDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewResponse {
        private String id;
        private String documentId;
        private String userId;
        private Integer rating;
        private String comment;
        private String reactionType;
        private String createdAt;
    }

    @Data
    public static class CreateReviewRequest {
        @NotBlank(message = "Document ID is required")
        private String documentId;
        private Integer rating;
        private String comment;
        private String reactionType;
    }
}
