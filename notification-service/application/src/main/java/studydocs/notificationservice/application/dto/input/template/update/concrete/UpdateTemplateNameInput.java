package studydocs.notificationservice.application.dto.input.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;

@Getter
public class UpdateTemplateNameInput extends UpdateTemplateByNameInput {
  private final String newName;

    public UpdateTemplateNameInput(String newName) {
        this.newName = newName;
    }
}
