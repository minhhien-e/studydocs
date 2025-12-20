package studydocs.notification.infrastructure.exception;

import studydocs.notification.infrastructure.exception.base.HttpException;

public class RemoteException extends HttpException {
    public RemoteException(int statusCode, Integer errorCode) {
        super("Failed to communicate with remote service", statusCode, errorCode);
    }
}

