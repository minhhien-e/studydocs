package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByChannelUseCasePort;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTemplateByChannelQueryHandler implements RequestHandler<BusRequestWrapper<GetTemplateByChannelQuery>, List<TemplateReadModel>> {
    private final GetTemplateByChannelUseCasePort useCase;

    @Override
    public List<TemplateReadModel> execute(BusRequestWrapper<GetTemplateByChannelQuery> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return GetTemplateByChannelQuery.class;
    }
}
