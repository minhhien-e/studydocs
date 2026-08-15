package com.studydocs.modules.academic.repository;

import com.studydocs.modules.academic.entity.DocumentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {
    List<DocumentEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    List<DocumentEntity> findByUserId(String userId);
    List<DocumentEntity> findAllByOrderByLikeCountDesc(Pageable pageable);
    List<DocumentEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
    long countByUserId(String userId);
}
