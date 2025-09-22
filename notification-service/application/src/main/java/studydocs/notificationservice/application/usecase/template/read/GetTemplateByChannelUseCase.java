package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;

import java.util.List;

public interface GetTemplateByChannelUseCase {
    List<TemplateDto> execute(GetTemplateByChannelInput inputModel);

}
