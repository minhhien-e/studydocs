package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import lombok.Setter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
@Setter
public class UpdateTemplateDescriptionInputModel extends UpdateTemplateByNameInputModel {
    private String newDescription;

    public UpdateTemplateDescriptionInputModel(String name) {
        super(name);
    }
}
