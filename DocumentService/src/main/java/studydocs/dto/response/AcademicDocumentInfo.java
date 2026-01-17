package studydocs.dto.response;

import java.util.UUID;

public record AcademicDocumentInfo(UUID subjectId, UUID universityId, String subjectName, String universityName) {
}
