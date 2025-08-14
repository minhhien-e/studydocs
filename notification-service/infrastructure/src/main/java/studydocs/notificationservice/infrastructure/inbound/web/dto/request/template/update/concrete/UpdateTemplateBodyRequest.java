package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.update.abstracts.UpdateTemplateByNameRequest;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateBodyInput;
@Getter
public class UpdateTemplateBodyRequest extends UpdateTemplateByNameRequest {
    private final String newBody;

    public UpdateTemplateBodyRequest(String newBody) {
        this.newBody = newBody;
    }
}
