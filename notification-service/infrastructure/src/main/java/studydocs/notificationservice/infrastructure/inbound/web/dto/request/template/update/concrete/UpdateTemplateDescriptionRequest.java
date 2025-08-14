package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateDescriptionInput;

public class UpdateTemplateDescriptionRequest extends UpdateTemplateByNameRequest {
    private final String newDescription;

    public UpdateTemplateDescriptionRequest(String newDescription) {
        this.newDescription = newDescription;
    }

    @Override
    protected UpdateTemplateByNameInput createInput() {
        return new UpdateTemplateDescriptionInput(newDescription);
    }

    public UpdateTemplateDescriptionInput toInput() {
        return (UpdateTemplateDescriptionInput) super.toInput();
    }
}
