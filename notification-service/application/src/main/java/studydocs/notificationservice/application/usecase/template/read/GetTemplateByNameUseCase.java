package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;

public interface GetTemplateByNameUseCase {
    TemplateOutput execute(GetTemplateByNameInput inputModel);
}
