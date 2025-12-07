package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.GetAllTemplateUseCasePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllTemplateQueryHandler implements RequestHandler<BusRequestWrapper<GetAllTemplateQuery>, List<TemplateReadModel>> {
    private final GetAllTemplateUseCasePort useCase;

    @Override
    public List<TemplateReadModel> execute(BusRequestWrapper<GetAllTemplateQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return GetAllTemplateQuery.class;
    }
}
