package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
public class UpdateTemplateBodyInputModel extends UpdateTemplateByNameInputModel {
    private final String newBody;

    public UpdateTemplateBodyInputModel(String newBody) {
       this.newBody = newBody;
    }
}
