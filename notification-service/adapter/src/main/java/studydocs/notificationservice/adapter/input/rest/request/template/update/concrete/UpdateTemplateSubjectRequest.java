package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateSubjectInputModel;
@Getter
public class UpdateTemplateSubjectRequest extends UpdateTemplateByNameRequest {
    private final String newSubject;

    public UpdateTemplateSubjectRequest(String newSubject) {
        this.newSubject = newSubject;
    }

    public UpdateTemplateSubjectInputModel toInputModel() {
        var inputModel =(UpdateTemplateSubjectInputModel) super.toInputModel();
        inputModel.setNewSubject(newSubject);
        return inputModel;
    }
}
