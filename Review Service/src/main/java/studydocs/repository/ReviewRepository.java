package studydocs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import studydocs.model.Review;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
    Page<Review> findByDocumentIdAndIsDeletedFalse(UUID documentId, Pageable pageable);

    @Aggregation(
            pipeline = {
                    "{ '$match': { 'documentId': ?0, 'isDeleted': false } }",
                    "{ '$group': { '_id': null, 'averageRating': { '$avg': '$rating' } } }"
            }
    )
    Double findAverageRatingByDocumentId(UUID documentId);
}