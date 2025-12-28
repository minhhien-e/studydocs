package studydocs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.model.DocumentReaction;

import java.util.Optional;
import java.util.UUID;

public interface DocumentReactionRepository extends MongoRepository<DocumentReaction, String> {
    Optional<DocumentReaction> findByDocumentIdAndUserId(UUID documentId, UUID userId);
}
