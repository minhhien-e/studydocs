package studydocs.notificationservice.application.service.usecase.notification.read;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read.GetNotificationByRecipientIdInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.notification.NotificationOutputModel;
import studydocs.notificationservice.application.port.input.dto.paging.SliceInput;
import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;
import studydocs.notificationservice.application.port.input.template.TemplateRenderer;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.application.utils.DataTestFactory;
import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.shared.enums.NotificationType;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetNotificationByRecipientIdUseCaseTests {
    @InjectMocks
    private GetNotificationByRecipientIdUseCaseImpl useCase;
    @Mock
    private NotificationRecipientRepositoryPort recipientRepositoryPort;
    @Mock
    private NotificationTemplateRepositoryPort templateRepositoryPort;
    @Mock
    private TemplateRenderer templateRenderer;
    @Captor
    ArgumentCaptor<UUID> templateIdCaptor;
    @Captor
    ArgumentCaptor<Map<String, Object>> dataCaptor;
    @Captor
    ArgumentCaptor<String> templateDataCaptor;

    private SliceInput<GetNotificationByRecipientIdInputModel> createSliceInput(UUID recipientId, LocalDateTime createdAt, int limit) {
        return new SliceInput<>(new GetNotificationByRecipientIdInputModel(recipientId, createdAt), limit);
    }

    private SliceOutput<NotificationRecipient> createSliceOutput(List<NotificationRecipient> content, boolean hasNext) {
        return new SliceOutput<>(content, hasNext);
    }

    private List<NotificationRecipient> createNotifications(int count, UUID recipientId, UUID templateId) {
        List<NotificationRecipient> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("test", "test");
            Notification notification = DataTestFactory.createNotification(templateId, UUID.randomUUID(), NotificationType.NEW_DOCUMENT.name(), templateData);
            NotificationRecipient recipient = DataTestFactory.createNotificationRecipient(recipientId, notification.getId(), notification);
            result.add(recipient);
        }
        return result;
    }

    @Test
    void getNotification_WhenValid_ShouldReturnNotifications() {
        //Arrange
        UUID recipientId = UUID.randomUUID();
        NotificationTemplate template = DataTestFactory.createNotificationTemplate();
        List<NotificationRecipient> recipients = createNotifications(5, recipientId, template.getId());
        SliceInput<GetNotificationByRecipientIdInputModel> input = createSliceInput(recipientId, LocalDateTime.now(), 10);
        GetNotificationByRecipientIdInputModel request = input.request();
        //Mock
        when(recipientRepositoryPort.findByRecipientId(request.recipientId(), request.createdAt(), input.limit())).thenReturn(createSliceOutput(recipients, false));
        recipients.forEach(recipient -> {
            Notification notification = recipient.getNotification();
            when(templateRepositoryPort.findById(notification.getTemplateId())).thenReturn(Optional.of(template));
            if (notification.getTemplateData() != null) {
                when(templateRenderer.render(template.getBodyTemplate().value(), notification.getTemplateData().data())).thenReturn("test");
            }
        });
        //Act
        SliceOutput<NotificationOutputModel> output = useCase.execute(input);
        //Assert
        assertEquals(output.content().size(), recipients.size());
        output.content().forEach(content -> {
            assertEquals("test", content.content());
        });
        verify(recipientRepositoryPort).findByRecipientId(request.recipientId(), request.createdAt(), input.limit());
        verify(templateRenderer, times(recipients.size())).render(templateDataCaptor.capture(), dataCaptor.capture());
        verify(templateRepositoryPort, times(recipients.size())).findById(templateIdCaptor.capture());
        List<String> capturedTemplates = templateDataCaptor.getAllValues();
        List<Map<String, Object>> capturedData = dataCaptor.getAllValues();
        List<UUID> capturedTemplateIds = templateIdCaptor.getAllValues();
        for (int i = 0; i < recipients.size(); i++) {
            Notification notification = recipients.get(i).getNotification();
            assertEquals(notification.getTemplateId(), capturedTemplateIds.get(i));
            if (notification.getTemplateData() != null) {
                assertEquals(template.getBodyTemplate().value(), capturedTemplates.get(i));
                assertEquals(notification.getTemplateData().data(), capturedData.get(i));
            }
        }
    }

    @Test
    void getNotification_WhenTemplateNotFound_ShouldThrowException() {
        //Arrange
        UUID recipientId = UUID.randomUUID();
        NotificationTemplate template = DataTestFactory.createNotificationTemplate();
        List<NotificationRecipient> recipients = createNotifications(5, recipientId, template.getId());
        SliceInput<GetNotificationByRecipientIdInputModel> input = createSliceInput(recipientId, LocalDateTime.now(), 10);
        GetNotificationByRecipientIdInputModel request = input.request();
        //Mock
        when(recipientRepositoryPort.findByRecipientId(request.recipientId(), request.createdAt(), input.limit())).thenReturn(createSliceOutput(recipients, false));
        recipients.forEach(recipient -> {
            Notification notification = recipient.getNotification();
            when(templateRepositoryPort.findById(notification.getTemplateId())).thenReturn(Optional.empty());
        });
        //Act & Assert
        assertThrows(TemplateNotFoundException.class, () -> {
            useCase.execute(input);
        });
        verify(recipientRepositoryPort).findByRecipientId(request.recipientId(), request.createdAt(), input.limit());
        verify(templateRepositoryPort, times(1)).findById(template.getId());
    }

}
