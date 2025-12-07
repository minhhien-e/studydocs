package studydocs.notification.application.usecase.template;

import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.SearchTemplateByNameUseCasePort;

import java.util.List;

@Service
public class SearchTemplateByNameUseCase implements SearchTemplateByNameUseCasePort {

    @Override
    public List<TemplateReadModel> execute(SearchTemplateByNameQuery params) {
        return null;
    }
}
