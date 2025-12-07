package studydocs.notification.application.dto.query.template;

import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.dto.base.Request;

import java.util.List;

public record GetTemplateByChannelQuery(String channel) implements Request<List<TemplateReadModel>> {
}
