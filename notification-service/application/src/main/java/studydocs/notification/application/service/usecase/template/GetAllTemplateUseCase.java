package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.port.in.usecase.template.GetAllTemplateUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateQueries;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllTemplateUseCase implements GetAllTemplateUseCasePort {
    private final NotificationTemplateQueries templateQueries;
    
    @Override
    public List<TemplateProjection> execute(GetAllTemplateQuery params) {
        return templateQueries.findAll();
    }

}
