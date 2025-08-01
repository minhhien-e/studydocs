package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import lombok.ToString;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
@ToString
public class UpdateTemplateSubjectInputModel extends UpdateTemplateByNameInputModel {
    private final String newSubject;

    public UpdateTemplateSubjectInputModel(String newSubject) {
        this.newSubject = newSubject;
    }
}
