package studydocs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import studydocs.model.DocumentReaction;

import java.util.Optional;
import java.util.UUID;

public interface DocumentReactionRepository extends MongoRepository<DocumentReaction, String> {
    Optional<DocumentReaction> findByDocumentIdAndUserId(UUID documentId, UUID userId);

    long countByType(studydocs.model.ReviewReaction.ReactionType type);

    long countByDocumentIdInAndType(java.util.List<UUID> documentIds, studydocs.model.ReviewReaction.ReactionType type);
}
