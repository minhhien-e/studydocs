package studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete;

import lombok.Getter;
import lombok.Setter;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.abstracts.UpdateTemplateByNameInputModel;

@Getter
@Setter
public class UpdateTemplateSubjectInputModel extends UpdateTemplateByNameInputModel {
    private String newSubject;

    public UpdateTemplateSubjectInputModel(String name) {
        super(name);
    }
}
