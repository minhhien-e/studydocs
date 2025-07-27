package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import lombok.Setter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
@Setter
public class UpdateTemplateNameInputModel extends UpdateTemplateByNameInputModel {
  private String newName;

    public UpdateTemplateNameInputModel(String name) {
        super(name);
    }
}
