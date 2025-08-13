package studydocs.notificationservice.application.dto.input.template.update.concrete;

import lombok.Getter;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;

@Getter
public class UpdateTemplateDescriptionInput extends UpdateTemplateByNameInput {
    private final String newDescription;

    public UpdateTemplateDescriptionInput(String newDescription) {
        this.newDescription = newDescription;
    }
}
