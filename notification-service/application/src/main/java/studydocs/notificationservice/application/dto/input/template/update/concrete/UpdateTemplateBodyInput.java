package studydocs.notificationservice.application.dto.input.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;

@Getter
public class UpdateTemplateBodyInput extends UpdateTemplateByNameInput {
    private final String newBody;

    public UpdateTemplateBodyInput(String newBody) {
       this.newBody = newBody;
    }
}
