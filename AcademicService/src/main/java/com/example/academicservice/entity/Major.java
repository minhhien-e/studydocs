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
        name = "majors",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_major_department_slug", columnNames = {"department_id", "slug"})
        },
        indexes = {
                @Index(name = "idx_major_department_active", columnList = "department_id, is_active"),
                @Index(name = "idx_major_department_created", columnList = "department_id, created_at DESC"),
                @Index(name = "idx_major_department_code", columnList = "department_id, code")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    // Quan hệ: nhiều Major thuộc 1 Department
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "department_id",
            nullable = false,
            columnDefinition = "CHAR(36)",
            foreignKey = @ForeignKey(name = "fk_major_department")
    )
    private Department department;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

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
