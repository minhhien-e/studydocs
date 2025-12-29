package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.port.in.usecase.template.SearchTemplateByNameUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

import java.util.List;

@Component
public class SearchTemplateByNameHandler
    extends AbstractHandler<SearchTemplateByNameQuery, List<TemplateProjection>> {
    
    protected SearchTemplateByNameHandler(SearchTemplateByNameUseCasePort useCase) {
        super(useCase, SearchTemplateByNameQuery.class);
    }
}
