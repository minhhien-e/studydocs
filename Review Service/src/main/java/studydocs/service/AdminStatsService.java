package studydocs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.model.ReviewReaction.ReactionType;
import studydocs.repository.DocumentReactionRepository;
import studydocs.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class AdminStatsService {

    private final ReviewRepository reviewRepository;
    private final DocumentReactionRepository documentReactionRepository;

    public long getTotalReviews() {
        return reviewRepository.count();
    }

    public long getTotalDocumentLikes() {
        return documentReactionRepository.countByType(ReactionType.LIKE);
    }

    public long countLikesForDocuments(java.util.List<java.util.UUID> documentIds) {
        if (documentIds == null || documentIds.isEmpty())
            return 0;
        return documentReactionRepository.countByDocumentIdInAndType(documentIds, ReactionType.LIKE);
    }
}
