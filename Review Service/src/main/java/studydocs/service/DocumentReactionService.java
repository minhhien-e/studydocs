package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.client.DocumentClient;
import studydocs.model.DocumentReaction;
import studydocs.model.DocumentStats;
import studydocs.model.ReviewReaction.ReactionType;
import studydocs.repository.DocumentReactionRepository;
import studydocs.repository.DocumentStatsRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentReactionService {

    private final DocumentReactionRepository reactionRepo;
    private final DocumentStatsRepository statsRepo;
    private final DocumentClient documentClient;

    @Transactional
    public void react(UUID documentId, UUID userId, String typeStr) {
        // Validation (Mocked or Real)
        documentClient.validateDocumentId(documentId);

        ReactionType type;
        try {
            type = ReactionType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type must be LIKE or DISLIKE");
        }

        DocumentStats stats = statsRepo.findById(documentId)
                .orElse(new DocumentStats(documentId, 0, 0));

        DocumentReaction existing = reactionRepo.findByDocumentIdAndUserId(documentId, userId).orElse(null);

        if (existing == null) {
            // New reaction
            reactionRepo.save(new DocumentReaction(documentId, userId, type));
            if (type == ReactionType.LIKE)
                stats.incrementLike();
            else
                stats.incrementDislike();

        } else if (existing.getType() == type) {
            // Remove reaction (Toggle off)
            reactionRepo.delete(existing);
            if (type == ReactionType.LIKE)
                stats.decrementLike();
            else
                stats.decrementDislike();

        } else {
            // Switch reaction (Like -> Dislike or vice versa)
            existing.setType(type);
            reactionRepo.save(existing);
            if (type == ReactionType.LIKE) {
                stats.incrementLike();
                stats.decrementDislike();
            } else {
                stats.incrementDislike();
                stats.decrementLike();
            }
        }

        statsRepo.save(stats);
    }
}
