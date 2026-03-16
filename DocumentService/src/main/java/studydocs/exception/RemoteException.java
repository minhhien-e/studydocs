package studydocs.exception;

import lombok.Getter;

@Getter
public class RemoteException extends RuntimeException {

    private final int statusCode;
    private final int errorCode = 504;

    public RemoteException(int statusCode) {
        super("Remote service error, status=" + statusCode);
        this.statusCode = statusCode;
    }
}
