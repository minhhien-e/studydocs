package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByChannelInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.port.repository.TemplateReadRepositoryPort;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByChannelUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetTemplateByChannelUseCaseImpl implements GetTemplateByChannelUseCase {
    private final TemplateReadRepositoryPort repository;

    @Override
    public List<TemplateDto> execute(GetTemplateByChannelInput inputModel) {
        var channel = inputModel.getChannel();
        return repository.findAllByChannel(channel);
    }
}
