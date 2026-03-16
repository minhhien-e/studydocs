package studydocs.notification.api.dto.request.template;

import lombok.Data;

import java.util.UUID;

@Data
public class EditTemplateSubjectRequest {
    private UUID templateId;
    private String newSubject;
}
