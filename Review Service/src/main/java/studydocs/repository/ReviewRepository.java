package studydocs.repository;

import studydocs.domain.Review;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;  // Import UUID

public interface ReviewRepository extends MongoRepository<Review, UUID> {  // Key type là UUID
    List<Review> findByDocumentIdAndIsDeletedFalse(Long documentId);

    @Aggregation(
            pipeline = {
                    "{ '$match': { 'documentId': ?0, 'isDeleted': false } }",
                    "{ '$group': { '_id': null, 'averageRating': { '$avg': '$rating' } } }"
            }
    )
    Double findAverageRatingByDocumentId(Long documentId);
}