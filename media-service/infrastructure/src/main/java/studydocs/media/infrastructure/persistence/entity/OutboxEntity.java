package studydocs.media.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "outbox_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEntity {
    @Id
    private UUID id;
    private String aggregateType;
    private UUID aggregateId;
    private String type;
    private String payload;
    private String status;

    @CreatedDate
    private LocalDateTime createdAt;
}
