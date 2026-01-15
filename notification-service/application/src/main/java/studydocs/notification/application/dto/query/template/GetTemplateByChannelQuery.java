package studydocs.notification.application.dto.query.template;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.dto.projection.TemplateProjection;

import java.util.List;

@Builder
public record GetTemplateByChannelQuery(String channel) implements Request<List<TemplateProjection>> {
}
