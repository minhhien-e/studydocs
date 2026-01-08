package studydocs.media.infrastructure.persistence.entity;

import io.github.infrastructure.mongo.entity.base.AggregateEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "files")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity extends AggregateEntity {
    private UUID uploaderId;
    private String fileName;
    private String publicId;
    private String resourceType;
    private Long size;
    private Integer totalPages;
    private String contentType;
    private LocalDateTime createdAt;
}
