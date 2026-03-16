package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface CountUnreadUseCasePort extends UseCase<Integer, CountUnreadQuery>{
}
