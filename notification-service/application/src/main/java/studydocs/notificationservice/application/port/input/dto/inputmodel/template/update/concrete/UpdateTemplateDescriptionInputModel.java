package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
public class UpdateTemplateDescriptionInputModel extends UpdateTemplateByNameInputModel {
    private final String newDescription;

    public UpdateTemplateDescriptionInputModel(String newDescription) {
        this.newDescription = newDescription;
    }
}
