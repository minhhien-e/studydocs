package studydocs.notificationservice.infrastructure.inbound.web.request.template.update.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;

@Data
public abstract class UpdateTemplateByNameRequest {
    @JsonIgnore
    protected String templateName;

    protected abstract UpdateTemplateByNameInput createInput();

    protected UpdateTemplateByNameInput toInput() {
        var input = createInput();
        input.setName(templateName);
        return input;
    }
}
