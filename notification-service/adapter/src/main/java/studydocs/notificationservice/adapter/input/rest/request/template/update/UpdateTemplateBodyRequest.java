package studydocs.notificationservice.adapter.input.rest.request.template.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateBodyInputModel;

public record UpdateTemplateBodyRequest(String templateName, String newBody) {
    public UpdateTemplateBodyInputModel toInputModel() {
        return new UpdateTemplateBodyInputModel(templateName, newBody);
    }
}
