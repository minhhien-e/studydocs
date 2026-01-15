package studydocs.media.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "assets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    private UUID uploaderId;
    private String assetName;
    private String publicId;
    private String resourceType;
    private Long size;
    private Integer totalPages;
    private String contentType;
    private String status;
    private Integer uploadProgress;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @Override
    public boolean isNew() {
        return version == null;
    }
}
