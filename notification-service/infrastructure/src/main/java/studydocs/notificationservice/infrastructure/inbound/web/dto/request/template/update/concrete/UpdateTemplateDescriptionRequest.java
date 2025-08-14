package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateDescriptionInput;

@Getter
public class UpdateTemplateDescriptionRequest extends UpdateTemplateByNameRequest {
    private final String newDescription;

    public UpdateTemplateDescriptionRequest(String newDescription) {
        this.newDescription = newDescription;
    }

}
