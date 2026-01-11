package studydocs.repository;

import studydocs.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {

    // Public queries: chỉ lấy review chưa xóa và chưa ẩn
    Page<Review> findByIsDeletedFalseAndIsHiddenFalse(Pageable pageable);

    Page<Review> findByDocumentIdAndIsDeletedFalseAndIsHiddenFalse(UUID documentId, Pageable pageable);

    Page<Review> findByUserIdAndIsDeletedFalseAndIsHiddenFalse(UUID userId, Pageable pageable);

    Optional<Review> findByIdAndIsDeletedFalseAndIsHiddenFalse(UUID id);

    // Admin queries: có thể thấy cả hidden
    Optional<Review> findByIdAndIsDeletedFalse(UUID id);

    // @SuppressWarnings("unused")
    Page<Review> findByDocumentIdAndIsDeletedFalse(UUID documentId, Pageable pageable);

    long countByUserIdAndIsDeletedFalseAndIsHiddenFalse(UUID userId);
}