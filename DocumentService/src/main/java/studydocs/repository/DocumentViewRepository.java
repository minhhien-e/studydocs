package studydocs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import studydocs.domain.DocumentView;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DocumentViewRepository extends JpaRepository<DocumentView, UUID> {
    Page<DocumentView> findByUserIdOrderByViewedAtDesc(UUID userId, Pageable pageable);

    // Optional: for cleaning up old views or ensuring unique daily views per
    // user/doc if required
    boolean existsByUserIdAndDocumentIdAndViewedAtAfter(UUID userId, UUID documentId, LocalDateTime after);
}
