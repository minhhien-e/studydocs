package studydocs.notificationservice.application.dto.input.template.update.concrete;

import lombok.Getter;
import lombok.ToString;
import studydocs.notificationservice.application.dto.input.template.update.abstracts.UpdateTemplateByNameInput;

@Getter
@ToString
public class UpdateTemplateSubjectInput extends UpdateTemplateByNameInput {
    private final String newSubject;

    public UpdateTemplateSubjectInput(String newSubject) {
        this.newSubject = newSubject;
    }
}
