package studydocs.repository;

import studydocs.model.Review;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
    List<Review> findByDocumentIdAndIsDeletedFalse(UUID documentId);

    @Aggregation(
            pipeline = {
                    "{ '$match': { 'documentId': ?0, 'isDeleted': false } }",
                    "{ '$group': { '_id': null, 'averageRating': { '$avg': '$rating' } } }"
            }
    )
    Double findAverageRatingByDocumentId(UUID documentId);
}