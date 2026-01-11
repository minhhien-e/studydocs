package studydocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "document_views", indexes = {
        @Index(name = "idx_view_user_id", columnList = "userId"),
        @Index(name = "idx_view_created_at", columnList = "viewedAt")
})
@Getter
@NoArgsConstructor
public class DocumentView {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID documentId;

    @Column(nullable = false)
    private UUID userId;

    private LocalDateTime viewedAt;

    public DocumentView(UUID documentId, UUID userId) {
        this.documentId = documentId;
        this.userId = userId;
        this.viewedAt = LocalDateTime.now();
    }
}
