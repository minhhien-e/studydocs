package studydocs.notificationservice.infrastructure.inbound.web.request.template.update.concrete;

import studydocs.notificationservice.infrastructure.inbound.web.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateBodyInput;

public class UpdateTemplateBodyRequest extends UpdateTemplateByNameRequest {
    private final String newBody;

    public UpdateTemplateBodyRequest(String newBody) {
        this.newBody = newBody;
    }

    @Override
    protected UpdateTemplateByNameInput createInput() {
        return new UpdateTemplateBodyInput(newBody);
    }

    public UpdateTemplateBodyInput toInput() {
        return (UpdateTemplateBodyInput) super.toInput();
    }
}
