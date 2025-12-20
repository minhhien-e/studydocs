package studydocs.notification.infrastructure.exception;


import studydocs.notification.shared.web.HttpException;

public class RemoteException extends HttpException {
    public RemoteException(int statusCode, Integer errorCode) {
        super("Failed to communicate with remote service", statusCode, errorCode);
    }
}

