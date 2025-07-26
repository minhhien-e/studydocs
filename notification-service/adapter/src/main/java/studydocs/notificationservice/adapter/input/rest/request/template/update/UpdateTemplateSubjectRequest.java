package studydocs.notificationservice.adapter.input.rest.request.template.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateSubjectInputModel;

public record UpdateTemplateSubjectRequest(String templateName, String newSubject) {
    public UpdateTemplateSubjectInputModel toInputModel() {
        return new UpdateTemplateSubjectInputModel(templateName, newSubject);
    }
}
