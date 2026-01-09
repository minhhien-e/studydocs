package studydocs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studydocs.domain.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByStatusAndUpdatedAtBefore(Document.Status status, LocalDateTime time);
    boolean existsByIdAndIsDeletedFalse(UUID id);
}