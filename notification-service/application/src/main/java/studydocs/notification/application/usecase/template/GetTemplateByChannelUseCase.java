package studydocs.notification.application.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.dto.readmodel.TemplateReadModel;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByChannelUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTemplateByChannelUseCase implements GetTemplateByChannelUseCasePort {
private final NotificationTemplateRepository templateRepository;
    @Override
    public List<TemplateReadModel> execute(GetTemplateByChannelQuery params) {
        return templateRepository.getByChannel(params.channel());
    }

}
