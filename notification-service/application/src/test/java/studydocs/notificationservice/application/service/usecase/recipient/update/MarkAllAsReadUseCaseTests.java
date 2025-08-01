package studydocs.notificationservice.application.service.usecase.recipient.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAllAsReadInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAsReadInputModel;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationAlreadyDeletedException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationsUnreadNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarkAllAsReadUseCaseTests {
    @Mock
    private NotificationRecipientRepositoryPort repository;

    @InjectMocks
    private MarkAllAsReadUseCaseImpl markAllAsReadUseCase;
    private final UUID recipientId = UUID.randomUUID();
    private static final long SUCCESS_UPDATE = 1L;
    private static final long FAIL_UPDATE = 0L;

    private MarkAllAsReadInputModel createMarkAllAsReadInputModel() {
        return new MarkAllAsReadInputModel(recipientId);
    }

    @Test
    public void markAllAsRead_WhenValid_ShouldUpdateSuccessfully() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.recipientId())).thenReturn(true);
        when(repository.markAllAsRead(input.recipientId())).thenReturn(SUCCESS_UPDATE);
        markAllAsReadUseCase.execute(input);
        verify(repository).hasAnyUnread(input.recipientId());
        verify(repository).markAllAsRead(input.recipientId());
    }

    @Test
    public void markAllAsRead_WhenNotificationsAlreadyRead_ShouldThrowException() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.recipientId())).thenReturn(false);
        assertThrows(NotificationsUnreadNotFoundException.class, () -> markAllAsReadUseCase.execute(input));
        verify(repository).hasAnyUnread(input.recipientId());
    }

    @Test
    public void markAsRead_WhenRepositoryFailsToUpdate_ShouldThrowException() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.recipientId())).thenReturn(true);
        when(repository.markAllAsRead(input.recipientId())).thenReturn(FAIL_UPDATE);
        assertThrows(UpdateFailedException.class, () -> markAllAsReadUseCase.execute(input));
        verify(repository).hasAnyUnread(input.recipientId());
        verify(repository).markAllAsRead(input.recipientId());
    }
}
