package studydocs.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;  // Import UUID

@Document(collection = "reviews")
@Data
@NoArgsConstructor
public class Review {
    @Id
    private UUID id;  // UUID object thay vì String

    @Indexed  // Tạo index để query nhanh trên documentId
    private Long documentId;  // ID của document được review

    @Indexed  // Tạo index để query nhanh trên userId
    private Long userId;  // ID người dùng tạo review

    private Integer rating;  // Điểm từ 1-5

    private String comment;  // Bình luận

    private Boolean isDeleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public Review(Long documentId, Long userId, Integer rating, String comment) {
        this.documentId = documentId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID();  // Generate UUID object
    }

    public void update(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}