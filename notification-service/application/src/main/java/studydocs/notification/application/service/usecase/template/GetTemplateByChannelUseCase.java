package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.projection.TemplateProjection;
import studydocs.notification.application.dto.query.template.GetTemplateByChannelQuery;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByChannelUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationTemplateQueries;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTemplateByChannelUseCase implements GetTemplateByChannelUseCasePort {
    private final NotificationTemplateQueries templateQueries;
    
    @Override
    public List<TemplateProjection> execute(GetTemplateByChannelQuery params) {
        return templateQueries.getByChannel(params.channel());
    }

}
