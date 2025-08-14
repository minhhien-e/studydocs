package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateSubjectInput;
@Getter
public class UpdateTemplateSubjectRequest extends UpdateTemplateByNameRequest {
    private final String newSubject;

    public UpdateTemplateSubjectRequest(String newSubject) {
        this.newSubject = newSubject;
    }

    @Override
    protected UpdateTemplateByNameInput createInput() {
        return new UpdateTemplateSubjectInput(newSubject);
    }

    public UpdateTemplateSubjectInput toInput() {
        return (UpdateTemplateSubjectInput) super.toInput();
    }
}
