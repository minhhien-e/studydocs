package com.studydocs.modules.system.entity;

import com.studydocs.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media_assets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaAssetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 500)
    private String fileUrl;

    @Column(length = 100)
    private String contentType;

    private Long sizeBytes;

    @Column(name = "user_id")
    private String userId;

    @Column(length = 50)
    @Builder.Default
    private String status = "COMPLETED";
}
