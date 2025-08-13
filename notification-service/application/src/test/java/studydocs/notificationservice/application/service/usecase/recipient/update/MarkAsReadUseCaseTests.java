package studydocs.notificationservice.application.service.usecase.recipient.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.domain.entity.NotificationRecipient;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationAlreadyDeletedException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarkAsReadUseCaseTests {
    @Mock
    private NotificationRecipientRepositoryPort repository;

    @InjectMocks
    private MarkAsReadUseCaseImpl markAsReadUseCase;
    private final UUID recipientId = UUID.randomUUID();
    private final UUID notificationId = UUID.randomUUID();
    private static final long SUCCESS_UPDATE = 1L;
    private static final long FAIL_UPDATE = 0L;

    private NotificationRecipient createRecipient(boolean isRead, boolean isDeleted) {
        return new NotificationRecipient(UUID.randomUUID(),
                recipientId,
                notificationId,
                isRead,
                isDeleted,
                null);
    }

    private MarkAsReadInput createMarkAsReadInputModel() {
        var input= new MarkAsReadInput( notificationId);
        input.setRecipientId(recipientId);
        return input;
    }

    @Test
    public void markAsRead_WhenValidAndNotDeleted_ShouldUpdateSuccessfully() {
        var inputModel = createMarkAsReadInputModel();
        NotificationRecipient recipient = createRecipient(false, false);
        when(repository.findByRecipientIdAndNotificationId(recipientId, notificationId)).thenReturn(Optional.of(recipient));
        when(repository.markAsRead(recipientId,notificationId)).thenReturn(SUCCESS_UPDATE);
        markAsReadUseCase.execute(inputModel);
        verify(repository).findByRecipientIdAndNotificationId(recipientId, notificationId);
        verify(repository).markAsRead(recipientId, notificationId);
    }

    @Test
    public void markAsRead_WhenNotificationAlreadyDeleted_ShouldThrowException() {
        var inputModel = createMarkAsReadInputModel();
        NotificationRecipient recipient = createRecipient(false, true);
        when(repository.findByRecipientIdAndNotificationId(recipientId, notificationId)).thenReturn(Optional.of(recipient));
        assertThrows(NotificationAlreadyDeletedException.class, () -> markAsReadUseCase.execute(inputModel));
        verify(repository).findByRecipientIdAndNotificationId(recipientId, notificationId);
    }

    @Test
    public void markAsRead_WhenRepositoryFailsToUpdate_ShouldThrowException() {
        var inputModel = createMarkAsReadInputModel();
        NotificationRecipient recipient = createRecipient(false, false);
        when(repository.findByRecipientIdAndNotificationId(recipientId, notificationId)).thenReturn(Optional.of(recipient));
        when(repository.markAsRead(recipientId, notificationId)).thenReturn(0L);
        assertThrows(UpdateFailedException.class, () -> markAsReadUseCase.execute(inputModel));
        verify(repository).findByRecipientIdAndNotificationId(recipientId, notificationId);
        verify(repository).markAsRead(recipientId, notificationId);
    }
    @Test
    public void markAsRead_WhenNotificationNotFound_ShouldThrowException() {
        var inputModel = createMarkAsReadInputModel();
        when(repository.findByRecipientIdAndNotificationId(recipientId, notificationId)).thenReturn(Optional.empty());
        assertThrows(NotificationNotFoundException.class, () -> markAsReadUseCase.execute(inputModel));
        verify(repository).findByRecipientIdAndNotificationId(recipientId, notificationId);
    }
}
