package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByChannelUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

import java.util.List;

@Component
public class GetTemplateByChannelHandler
    extends AbstractHandler<GetTemplateByChannelQuery, List<TemplateProjection>> {
    
    protected GetTemplateByChannelHandler(GetTemplateByChannelUseCasePort useCase) {
        super(useCase, GetTemplateByChannelQuery.class);
    }
}
