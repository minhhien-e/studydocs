package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;

import java.util.List;

public interface GetAllTemplateUseCase {
    List<TemplateDto> execute(GetAllTemplateInput inputModel);

}
