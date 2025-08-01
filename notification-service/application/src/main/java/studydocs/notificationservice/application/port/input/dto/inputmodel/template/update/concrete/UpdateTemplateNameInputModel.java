package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
public class UpdateTemplateNameInputModel extends UpdateTemplateByNameInputModel {
  private final String newName;

    public UpdateTemplateNameInputModel(String newName) {
        this.newName = newName;
    }
}
