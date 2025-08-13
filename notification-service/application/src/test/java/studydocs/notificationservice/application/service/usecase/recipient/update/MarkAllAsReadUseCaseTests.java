package studydocs.notificationservice.application.service.usecase.recipient.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationsUnreadNotFoundException;

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

    private MarkAllAsReadInput createMarkAllAsReadInputModel() {
        var input = new MarkAllAsReadInput();
        input.setRecipientId(recipientId);
        return input;
    }

    @Test
    public void markAllAsRead_WhenValid_ShouldUpdateSuccessfully() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.getRecipientId())).thenReturn(true);
        when(repository.markAllAsRead(input.getRecipientId())).thenReturn(SUCCESS_UPDATE);
        markAllAsReadUseCase.execute(input);
        verify(repository).hasAnyUnread(input.getRecipientId());
        verify(repository).markAllAsRead(input.getRecipientId());
    }

    @Test
    public void markAllAsRead_WhenNotificationsAlreadyRead_ShouldThrowException() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.getRecipientId())).thenReturn(false);
        assertThrows(NotificationsUnreadNotFoundException.class, () -> markAllAsReadUseCase.execute(input));
        verify(repository).hasAnyUnread(input.getRecipientId());
    }

    @Test
    public void markAsRead_WhenRepositoryFailsToUpdate_ShouldThrowException() {
        var input = createMarkAllAsReadInputModel();
        when(repository.hasAnyUnread(input.getRecipientId())).thenReturn(true);
        when(repository.markAllAsRead(input.getRecipientId())).thenReturn(FAIL_UPDATE);
        assertThrows(UpdateFailedException.class, () -> markAllAsReadUseCase.execute(input));
        verify(repository).hasAnyUnread(input.getRecipientId());
        verify(repository).markAllAsRead(input.getRecipientId());
    }
}
