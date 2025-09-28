package studydocs.notificationservice.application.service.usecase.recipient.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.mapper.NotificationMapper;
import studydocs.notificationservice.application.dto.output.NotificationDto;
import studydocs.notificationservice.application.dto.output.UserNotificationDto;
import studydocs.notificationservice.application.port.render.TemplateRenderer;
import studydocs.notificationservice.application.port.repository.RecipientReadRepositoryPort;
import studydocs.notificationservice.application.port.repository.TemplateReadRepositoryPort;
import studydocs.notificationservice.application.usecase.recipient.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetNotificationByRecipientIdUseCaseImpl implements GetNotificationByRecipientIdUseCase {
    private final RecipientReadRepositoryPort recipientRepositoryPort;
    private final TemplateReadRepositoryPort templateRepositoryPort;
    private final TemplateRenderer templateRenderer;

    public GetNotificationByRecipientIdUseCaseImpl(RecipientReadRepositoryPort recipientRepositoryPort,
                                                   TemplateReadRepositoryPort templateRepositoryPort,
                                                   @Qualifier("plainTextTemplateRenderer") TemplateRenderer templateRenderer) {
        this.recipientRepositoryPort = recipientRepositoryPort;
        this.templateRepositoryPort = templateRepositoryPort;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public SliceOutput<NotificationDto> execute(SliceInput<GetNotificationByRecipientIdInput> inputModel) {
        var sliceOutput = findByRecipientId(inputModel);
        List<NotificationDto> outputModels = new ArrayList<>();
        sliceOutput.content().forEach(userNotificationDto -> {
            var template = templateRepositoryPort.getById(userNotificationDto.templateId());
            String content = templateRenderer.render(template.bodyTemplate(), userNotificationDto.data());
            outputModels.add(NotificationMapper.toDto(userNotificationDto, template, content));
        });
        return new SliceOutput<>(outputModels, sliceOutput.hasNext());
    }

    private SliceOutput<UserNotificationDto> findByRecipientId(SliceInput<GetNotificationByRecipientIdInput> inputModel) {
        var request = inputModel.request();
        return recipientRepositoryPort.findByRecipientId(request.recipientId(),
                request.isDeleted(),
                request.createdAt(),
                inputModel.limit());
    }

}
