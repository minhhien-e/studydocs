package com.example.authservicev2.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "roles",
        indexes = {
                @Index(name = "idx_name", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Lob
    private String description;

    @Column(name = "is_system_role", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isSystemRole = false;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer level = 0;

    @Column(name = "created_at",
            nullable = false,
            updatable = false,
            insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at",
            nullable = false,
            insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updatedAt;
}
