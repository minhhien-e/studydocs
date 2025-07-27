package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateBodyInputModel;

public class UpdateTemplateBodyRequest extends UpdateTemplateByNameRequest {
    private final String newBody;

    public UpdateTemplateBodyRequest(String newBody) {
        this.newBody = newBody;
    }

    public UpdateTemplateBodyInputModel toInputModel() {
        var inputModel = (UpdateTemplateBodyInputModel) super.toInputModel();
        inputModel.setNewBody(newBody);
        return inputModel;
    }
}
