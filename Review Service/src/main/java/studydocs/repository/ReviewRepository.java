package studydocs.repository;

import studydocs.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {

    Page<Review> findByIsDeletedFalse(Pageable pageable);
    Page<Review> findByDocumentIdAndIsDeletedFalse(UUID documentId, Pageable pageable);
    Page<Review> findByUserIdAndIsDeletedFalse(UUID userId, Pageable pageable);
    Optional<Review> findByIdAndIsDeletedFalse(UUID id);

    @Aggregation({
            "{ '$match': { 'documentId': ?0, 'isDeleted': false } }",
            "{ '$group': { '_id': null, 'averageRating': { '$avg': '$rating' } } }"
    })
    Double findAverageRatingByDocumentId(UUID docId);
}
