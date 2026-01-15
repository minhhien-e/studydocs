package studydocs.notification.application.dto.query.template;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.dto.projection.TemplateProjection;

@Builder
public record GetTemplateByNameQuery(String name) implements Request<TemplateProjection> {
}
