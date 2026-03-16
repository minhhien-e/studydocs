package studydocs.repository;

import org.springframework.data.mongodb.repository.Query;
import studydocs.model.ReviewReaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewReactionRepository extends MongoRepository<ReviewReaction, String> {

    Optional<ReviewReaction> findByReviewIdAndUserId(UUID reviewId, UUID userId);
//    @SuppressWarnings("unused")
    @Query("{ 'reviewId' : ?0, 'userId' : ?1 }")
    void deleteByReviewIdAndUserId(UUID reviewId, UUID userId);
}