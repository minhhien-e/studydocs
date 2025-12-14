package studydocs.notification.application.port.out.remote;

import studydocs.notification.application.dto.view.UserView;

public interface RemoteUserServicePort {
    UserView getById();
}
