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
    private final studydocs.repository.DocumentStatsRepository documentStatsRepository;

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

    public java.util.List<studydocs.model.DocumentStats> getTopLikedDocuments() {
        return documentStatsRepository.findTop10ByOrderByLikeCountDesc();
    }
}
// Note: BeanUtils is a hack if I can't easily inject. But wait, I can inject
// DocumentStatsRepository.
// Let me check if DocumentStatsRepository is already injected.
// Looking at the file content from step 104, it is NOT injected. Only
// ReviewRepository and DocumentReactionRepository.
// So I should inject it using constructor.
