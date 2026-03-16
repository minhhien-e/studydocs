package studydocs.notification.application.port.in.usecase.template;

import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.List;

public interface GetAllTemplateUseCasePort extends UseCase<List<TemplateProjection>, GetAllTemplateQuery>{
}
