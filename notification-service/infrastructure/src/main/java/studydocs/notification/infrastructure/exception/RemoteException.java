package studydocs.notification.infrastructure.exception;

public class RemoteException extends HttpException {
    public RemoteException(int statusCode, Integer errorCode) {
        super("", statusCode, errorCode);
    }
}

