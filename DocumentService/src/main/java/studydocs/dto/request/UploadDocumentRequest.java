package studydocs.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class UploadDocumentRequest {
    private UUID userId;
    private String title;
    private String description;
    private UUID universityId;
    private UUID subjectId;
    private String schoolYear;
}