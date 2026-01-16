package studydocs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studydocs.domain.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
        List<Document> findByStatusAndUpdatedAtBefore(Document.Status status, LocalDateTime time);

        boolean existsByIdAndIsDeletedFalse(UUID id);

        org.springframework.data.domain.Page<Document> findByIsDeletedFalse(
                        org.springframework.data.domain.Pageable pageable);

        org.springframework.data.domain.Page<Document> findByUserIdAndIsDeletedFalse(UUID userId,
                        org.springframework.data.domain.Pageable pageable);

        long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

        long countByUserIdAndCreatedAtBetween(UUID userId, LocalDateTime start, LocalDateTime end);

        long countByUserIdAndIsDeletedFalse(UUID userId);

        @org.springframework.data.jpa.repository.Query("SELECT d.id FROM Document d WHERE d.userId = :userId AND d.isDeleted = false")
        List<UUID> findIdsByUserIdAndIsDeletedFalse(
                        @org.springframework.data.repository.query.Param("userId") UUID userId);

        java.util.Optional<Document> findByIdAndUserId(UUID id, UUID userId);
}