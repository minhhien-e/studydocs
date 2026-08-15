package com.studydocs.modules.academic.entity;

import com.studydocs.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 100)
    private String code;

    @Column(length = 100)
    private String slug;

    @Column(length = 500)
    private String description;

    @Column(name = "university_id", nullable = false)
    private Long universityId;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}
