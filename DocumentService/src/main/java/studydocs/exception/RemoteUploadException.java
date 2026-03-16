package studydocs.exception;


import lombok.Getter;

@Getter
public class RemoteUploadException extends RuntimeException {
    private final int errorCode = 450;
    public RemoteUploadException(String message) {
        super(message);
    }
}