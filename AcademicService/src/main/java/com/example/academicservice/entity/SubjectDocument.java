package com.example.academicservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(
        name = "subject_documents",
        indexes = {
                @Index(name = "idx_subject_document_subject", columnList = "subject_id, is_active"),
                @Index(name = "idx_subject_document_created", columnList = "subject_id, created_at DESC")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    // Quan hệ: nhiều Document thuộc 1 Subject
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "subject_id",
            nullable = false,
            columnDefinition = "CHAR(36)",
            foreignKey = @ForeignKey(name = "fk_subject_document_subject")
    )
    private Subject subject;

    @Column(name = "document_id", nullable = false)
    private String documentId; // ID của document từ Document Service

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
