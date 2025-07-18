package studydocs.notificationservice.application.port.input.usecase.template.read;

import studydocs.notificationservice.application.port.input.dto.inputmodel.template.read.GetTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.template.TemplateOutputModel;

public interface GetTemplateByNameUseCase {
    TemplateOutputModel execute(GetTemplateByNameInputModel inputModel);
}
