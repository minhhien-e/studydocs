package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.port.in.usecase.template.GetAllTemplateUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateQueries;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class GetAllTemplateUseCase implements GetAllTemplateUseCasePort {
    private final NotificationTemplateQueries templateQueries;
    
    @Override
    public List<TemplateProjection> execute(GetAllTemplateQuery params) {
        return templateQueries.findAll();
    }

}
