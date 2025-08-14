package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;

@Getter
public class UpdateTemplateSubjectRequest extends UpdateTemplateByNameRequest {
    private final String newSubject;

    public UpdateTemplateSubjectRequest(String newSubject) {
        this.newSubject = newSubject;
    }

}
