package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import lombok.Setter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
@Setter
public class UpdateTemplateBodyInputModel extends UpdateTemplateByNameInputModel {
    private String newBody;

    public UpdateTemplateBodyInputModel(String name) {
        super(name);
    }
}
