package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.template.AddTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.AddTemplateUseCasePort;
import studydocs.notification.domain.aggregate.NotificationTemplate;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AddTemplateUseCase implements AddTemplateUseCasePort {
    private final NotificationTemplateRepository templateRepository;

    @Override
    public Void execute(AddTemplateCommand params) {
        var template = NotificationTemplate.create(params.name(), params.channel(), params.subjectTemplate(), params.bodyTemplate(), params.description());
        templateRepository.save(template);
        return null;
    }
}
