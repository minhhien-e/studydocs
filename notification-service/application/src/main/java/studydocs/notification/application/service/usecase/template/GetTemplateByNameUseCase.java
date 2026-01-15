package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetTemplateByNameQuery;
import studydocs.notification.application.dto.query.template.SearchTemplateByNameQuery;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByNameUseCasePort;
import studydocs.notification.application.port.in.usecase.template.SearchTemplateByNameUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateQueries;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTemplateByNameUseCase implements GetTemplateByNameUseCasePort {
    private final NotificationTemplateQueries notificationTemplateQueries;
    @Override
    public TemplateProjection execute(GetTemplateByNameQuery params) {
        return notificationTemplateQueries.getByName(params.name());
    }
}
