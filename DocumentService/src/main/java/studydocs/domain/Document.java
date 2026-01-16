package studydocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter
@NoArgsConstructor
public class Document {
    public enum Status {
        PENDING, UPLOADING, UPLOADED, FAILED
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true, columnDefinition = "BINARY(16)")
    private UUID fileId;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    public Document(UUID userId, String title, String description, String schoolYear) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.schoolYear = schoolYear;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public void markUploading() {
        this.status = Status.UPLOADING;
        this.updatedAt = LocalDateTime.now();
    }

    public void markUploaded() {
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
        this.updatedAt = LocalDateTime.now();
    }

}