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
        name = "departments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_department_faculty_slug", columnNames = {"faculty_id", "slug"})
        },
        indexes = {
                @Index(name = "idx_department_faculty_active", columnList = "faculty_id, is_active"),
                @Index(name = "idx_department_faculty_created", columnList = "faculty_id, created_at DESC"),
                @Index(name = "idx_department_faculty_code", columnList = "faculty_id, code")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    /**
     * Mối quan hệ N-1 (nhiều department thuộc 1 faculty)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false, columnDefinition = "CHAR(36)",
            foreignKey = @ForeignKey(name = "fk_department_faculty"))
    private Faculty faculty;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String code;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
