package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateNameInput;

@Getter
public class UpdateTemplateNameRequest extends UpdateTemplateByNameRequest {
    private final String newName;

    public UpdateTemplateNameRequest(String newName) {
        this.newName = newName;
    }
}
