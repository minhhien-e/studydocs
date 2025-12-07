package studydocs.notification.application.dto.query.template;


import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.dto.base.Request;

import java.util.List;

public record GetAllTemplateQuery() implements Request<List<TemplateReadModel>> {
}
