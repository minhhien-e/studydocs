package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.port.in.usecase.template.GetAllTemplateUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

import java.util.List;

@Component
public class GetAllTemplateQueryHandler 
    extends AbstractHandler<GetAllTemplateQuery, List<TemplateProjection>, GetAllTemplateUseCasePort> {
    
    protected GetAllTemplateQueryHandler(GetAllTemplateUseCasePort useCase) {
        super(useCase, GetAllTemplateQuery.class);
    }
}
