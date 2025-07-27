package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateDescriptionInputModel;

public class UpdateTemplateDescriptionRequest extends UpdateTemplateByNameRequest {
    private final String newDescription;

    public UpdateTemplateDescriptionRequest(String newDescription) {
        this.newDescription = newDescription;
    }

    public UpdateTemplateDescriptionInputModel toInputModel() {
        var inputModel = (UpdateTemplateDescriptionInputModel) super.toInputModel();
        inputModel.setNewDescription(newDescription);
        return inputModel;
    }
}
