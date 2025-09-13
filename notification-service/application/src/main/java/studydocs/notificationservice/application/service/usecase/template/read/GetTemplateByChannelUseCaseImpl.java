package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByChannelUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetTemplateByChannelUseCaseImpl implements GetTemplateByChannelUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public List<TemplateOutput> execute(GetTemplateByChannelInput inputModel) {
        return repository.findByChannel(inputModel.getChannel()).stream()
                .map(TemplateOutput::toOutput).toList();
    }
}
