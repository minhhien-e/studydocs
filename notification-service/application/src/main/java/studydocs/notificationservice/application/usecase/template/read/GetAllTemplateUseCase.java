package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;

import java.util.List;

public interface GetAllTemplateUseCase {
    List<TemplateOutput> execute(GetAllTemplateInput inputModel);

}
