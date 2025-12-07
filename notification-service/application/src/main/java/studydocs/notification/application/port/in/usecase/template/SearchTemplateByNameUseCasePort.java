package studydocs.notification.application.port.in.usecase.template;

import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.List;

public interface SearchTemplateByNameUseCasePort extends UseCase<List<TemplateReadModel>, SearchTemplateByNameQuery>{
}
