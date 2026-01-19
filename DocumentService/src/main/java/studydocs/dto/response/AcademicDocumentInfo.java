package studydocs.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record AcademicDocumentInfo(
        @JsonProperty("subjectId") UUID subjectId,
        @JsonProperty("universityId") UUID universityId) {
}
