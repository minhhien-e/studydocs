package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.dto.readmodel.NotificationReadModel;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.List;

public interface GetNotificationByRecipientIdUseCasePort extends UseCase<List<NotificationReadModel>, GetNotificationByRecipientIdQuery>{
}
