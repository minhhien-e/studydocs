package com.studydocs.modules.review.repository;

import com.studydocs.modules.review.entity.DocumentReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<DocumentReviewEntity, String> {
    List<DocumentReviewEntity> findByDocumentId(String documentId);
    Optional<DocumentReviewEntity> findByDocumentIdAndUserId(String documentId, String userId);
    long countByUserId(String userId);
    long countByUserIdAndReactionTypeNotNull(String userId);
}
