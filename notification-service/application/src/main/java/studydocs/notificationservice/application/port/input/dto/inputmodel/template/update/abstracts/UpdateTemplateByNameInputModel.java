package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts;

import lombok.Data;

@Data
public class UpdateTemplateByNameInputModel {
    protected String name;

    public UpdateTemplateByNameInputModel(final String name) {
        this.name = name;
    }
}
