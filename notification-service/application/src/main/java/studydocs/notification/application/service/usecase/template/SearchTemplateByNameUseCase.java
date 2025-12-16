package studydocs.notification.application.service.usecase.template;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.port.in.usecase.template.SearchTemplateByNameUseCasePort;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SearchTemplateByNameUseCase implements SearchTemplateByNameUseCasePort {
    
    @Override
    public List<TemplateProjection> execute(SearchTemplateByNameQuery params) {
        // TODO: Implement template search by name
        return List.of();
    }
}
