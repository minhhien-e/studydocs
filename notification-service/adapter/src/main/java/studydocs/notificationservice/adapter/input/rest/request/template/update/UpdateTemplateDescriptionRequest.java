package studydocs.notificationservice.adapter.input.rest.request.template.update;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateDescriptionInputModel;

public record UpdateTemplateDescriptionRequest(String templateName, String newDescription) {
    public UpdateTemplateDescriptionInputModel toInputModel() {
        return new UpdateTemplateDescriptionInputModel( templateName, newDescription);
    }
}
