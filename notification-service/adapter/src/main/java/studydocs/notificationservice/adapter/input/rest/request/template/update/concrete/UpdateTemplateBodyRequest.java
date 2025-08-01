package studydocs.notificationservice.adapter.input.rest.request.template.update.concrete;

import studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateBodyInputModel;

public class UpdateTemplateBodyRequest extends UpdateTemplateByNameRequest {
    private final String newBody;

    public UpdateTemplateBodyRequest(String newBody) {
        this.newBody = newBody;
    }

    @Override
    protected UpdateTemplateByNameInputModel createInputModel() {
        return new UpdateTemplateBodyInputModel(newBody);
    }

    public UpdateTemplateBodyInputModel toInputModel() {
        return (UpdateTemplateBodyInputModel) super.toInputModel();
    }
}
