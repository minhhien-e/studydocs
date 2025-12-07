package studydocs.notification.application.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.query.template.GetAllTemplateQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.GetAllTemplateUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllTemplateUseCase implements GetAllTemplateUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;
    @Override
    public List<TemplateReadModel> execute(GetAllTemplateQuery params) {
        return notificationTemplateRepository.findAll();
    }

}
