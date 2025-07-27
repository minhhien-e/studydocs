package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateNameInputModel;

public class UpdateTemplateNameRequest extends UpdateTemplateByNameRequest {
    private final String newName;

    public UpdateTemplateNameRequest(String newName) {
        this.newName = newName;
    }

    public UpdateTemplateNameInputModel toInputModel() {
        var inputModel = (UpdateTemplateNameInputModel) super.toInputModel();
        inputModel.setNewName(newName);
        return inputModel;
    }
}
