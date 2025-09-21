package studydocs.notificationservice.application.usecase.template.read;

import studydocs.notificationservice.application.dto.input.template.read.SearchTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateOutput;

import java.util.List;

public interface SearchTemplateByNameUseCase {
    List<TemplateOutput> execute(SearchTemplateByNameInput inputModel);

}
