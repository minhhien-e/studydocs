package studydocs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studydocs.domain.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
