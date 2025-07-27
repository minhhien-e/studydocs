package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateDescriptionInputModel;

public class UpdateTemplateDescriptionRequest extends UpdateTemplateByNameRequest {
    private final String newDescription;

    public UpdateTemplateDescriptionRequest(String newDescription) {
        this.newDescription = newDescription;
    }

    @Override
    protected UpdateTemplateByNameInputModel createInputModel() {
        return new UpdateTemplateDescriptionInputModel(newDescription);
    }

    public UpdateTemplateDescriptionInputModel toInputModel() {
        return (UpdateTemplateDescriptionInputModel) super.toInputModel();
    }
}
