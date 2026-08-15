package com.studydocs.modules.academic.entity;

import com.studydocs.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String fileUrl;

    @Column(length = 500)
    private String thumbnailUrl;

    @Column(length = 100)
    private String category;

    @Column(length = 255)
    private String school;

    @Column(name = "page_count")
    @Builder.Default
    private Integer pageCount = 1;

    @Column(length = 10)
    private String academicYear;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "like_count")
    @Builder.Default
    private Integer likeCount = 0;

    @Column(name = "comment_count")
    @Builder.Default
    private Integer commentCount = 0;

    @Column(name = "download_count")
    @Builder.Default
    private Integer downloadCount = 0;
}
