package studydocs.notification.application.dto.query.template;

import lombok.Builder;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.base.Request;

import java.util.List;

@Builder
public record GetAllTemplateQuery() implements Request<List<TemplateProjection>> {
}
