package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.SearchTemplateByNameUseCasePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchTemplateByNameQueryHandler implements RequestHandler<BusRequestWrapper<SearchTemplateByNameQuery>, List<TemplateReadModel>> {
    private final SearchTemplateByNameUseCasePort useCase;

    @Override
    public List<TemplateReadModel> execute(BusRequestWrapper<SearchTemplateByNameQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return SearchTemplateByNameQuery.class;
    }
}
