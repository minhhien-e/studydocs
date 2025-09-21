package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.output.TemplateOutput;

import java.util.List;

public interface GetTemplateByChannelUseCase {
    List<TemplateOutput> execute(GetTemplateByChannelInput inputModel);

}
