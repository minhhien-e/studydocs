package studydocs.notificationservice.adapter.input.rest.request.template.update.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Data
public abstract class UpdateTemplateByNameRequest {
    @JsonIgnore
    protected String templateName;

    protected UpdateTemplateByNameInputModel toInputModel() {
        return new UpdateTemplateByNameInputModel(templateName);
    }
}
