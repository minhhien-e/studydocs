package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;

public interface GetTemplateByNameUseCase {
    TemplateDto execute(GetTemplateByNameInput inputModel);
}
