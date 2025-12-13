package studydocs.notification.application.dto.query.template;

import lombok.Builder;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.dto.base.Request;

import java.util.List;

@Builder
public record SearchTemplateByNameQuery(String name) implements Request<List<TemplateReadModel>> {
}
