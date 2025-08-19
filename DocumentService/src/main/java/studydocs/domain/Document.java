package studydocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@NoArgsConstructor
public class Document {
    public enum Status { PENDING, UPLOADING, UPLOADED, FAILED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long userId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String fileUrl;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    public Document(Long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public void markUploading() {
        this.status = Status.UPLOADING;
        this.updatedAt = LocalDateTime.now();
    }

    public void markUploaded(String fileUrl) {
        this.fileUrl = fileUrl;
        this.status = Status.UPLOADED;
        this.updatedAt = LocalDateTime.now();
    }

    public void markFailed(String reason) {
        this.status = Status.FAILED;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
