package studydocs.notificationservice.infrastructure.inbound.web.request.template.update.concrete;

import studydocs.notificationservice.infrastructure.inbound.web.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateNameInput;

public class UpdateTemplateNameRequest extends UpdateTemplateByNameRequest {
    private final String newName;

    public UpdateTemplateNameRequest(String newName) {
        this.newName = newName;
    }
    @Override
    protected UpdateTemplateByNameInput createInput() {
        return new UpdateTemplateNameInput(newName);
    }

    public UpdateTemplateNameInput toInput() {
        return (UpdateTemplateNameInput) super.toInput();
    }
}
