package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateNameInputModel;

public class UpdateTemplateNameRequest extends UpdateTemplateByNameRequest {
    private final String newName;

    public UpdateTemplateNameRequest(String newName) {
        this.newName = newName;
    }
    @Override
    protected UpdateTemplateByNameInputModel createInputModel() {
        return new UpdateTemplateNameInputModel(newName);
    }

    public UpdateTemplateNameInputModel toInputModel() {
        return (UpdateTemplateNameInputModel) super.toInputModel();
    }
}
